package computer_architecture;


/**
 * Created by Sang on 10/17/17.
 */
public class Cache {
    final int offsetMask = 0xF;
    final int slotMask = 0xF0;
    final int tagMask = 0x700;

    short[] mainMemory;

    Slot[] data;

    public Cache(){
        mainMemory = new short[0x800];
        for (int i = 0; i < mainMemory.length; i++) {
            mainMemory[i] = (short)(i % 0x100);
        }
        data = new Slot[0x10];
        for (int i = 0; i < data.length; i++) {
            data[i] = new Slot();
        }
    }

    public Result read(int address){
        int offset = address & offsetMask;
        int slotNum = (address & slotMask) >>> 4;
        int tag = (address & tagMask) >>> 8;
        Slot slot = data[slotNum];
        if(slot.valid && slot.tag == tag){
            return new Result(true, slot.data[offset]);
        }else if(slot.valid && slot.tag != tag){
            writeBlockToMemory(getStartAddress(switchAddressTag(slot.tag, address)), slot);
            readBlockFromMemory(getStartAddress(address), slot);
            slot.tag = (short)tag;
            return new Result(false, slot.data[offset]);
        }else{
            readBlockFromMemory(getStartAddress(address), slot);
            slot.valid = true;
            slot.tag = (short)tag;
            return new Result(false, slot.data[offset]);
        }
    }

    private int switchAddressTag(int tag, int address){
        return (tag << 8) + (address & 0x0FF);
    }

    private int getStartAddress(int address){
        return address & 0xFF0;
    }

    private void readBlockFromMemory(int startAddress, Slot slot){
        for(int i = startAddress; i <= startAddress + 0xF ; i++){
            slot.data[i - startAddress] = mainMemory[i];
        }
    }

    private void writeBlockToMemory(int startAddress, Slot slot){
        for(int i = startAddress; i <= startAddress + 0xF ; i++){
            mainMemory[i] = slot.data[i - startAddress];
        }
    }

    public Result write(int address, short data){
        int offset = address & offsetMask;
        int slotNum = (address & slotMask) >>> 4;
        int tag = (address & tagMask) >>> 8;
        Slot slot = this.data[slotNum];
        if(slot.valid && slot.tag == tag){
            slot.data[offset] = data;
            return new Result(true, data);
        }else if(slot.valid && slot.tag != tag){
            writeBlockToMemory(getStartAddress(switchAddressTag(slot.tag, address)), slot);
            readBlockFromMemory(getStartAddress(address), slot);
            slot.data[offset] = data;
            slot.tag = (short)tag;
            return new Result(false, slot.data[offset]);
        }else{
            readBlockFromMemory(getStartAddress(address), slot);
            slot.data[offset] = data;
            slot.valid = true;
            slot.tag = (short)tag;
            return new Result(false, data);
        }
    }

    public String display(){
        StringBuffer sb = new StringBuffer();
        sb.append("Slot\tValid\tTag\t\tData\n");
        for (int i = 0; i < this.data.length; i++) {
            Slot slot = data[i];
            sb.append(String.format("%X   \t%s    \t%s\t\t",i,slot.valid?"1":"0",slot.tag));
            for(int j = 0; j < slot.data.length; j++){
                sb.append(String.format("%02X  ",slot.data[j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
