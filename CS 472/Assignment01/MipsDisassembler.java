package main.homework;

import java.util.*;

/**
 * Created by Sang on 2017/9/12.
 */
public class MipsDisassembler {
    int opcode;
    int opcodeMask = 0xFC000000;
    int sourceRegA;
    int sourceRegAMask = 0x03E00000;
    int sourceRegB;
    int sourceRegBMask = 0x001F0000;
    int destReg;
    int destRegMask = 0x0000F800;
    int func;
    int funcMask = 0x0000003F;
    short offset;
    int offsetMask = 0x0000FFFF;
    int destAddress;
    int beginingAddress = 0x9A040;
    static Map funcMap = new HashMap();
    static Map opcodeMap = new HashMap();
    static int[] instructionList = new int[]{
            0x032BA020,
            0x8CE90014,
            0x12A90003,
            0x022DA822,
            0xADB30020,
            0x02697824,
            0xAE8FFFF4,
            0x018C6020,
            0x02A4A825,
            0x158FFFF7,
            0x8ECDFFF0
    };

    {
        funcMap.put(0x20,"add");
        funcMap.put(0x22,"sub");
        funcMap.put(0x24,"and");
        funcMap.put(0x25,"or");
        funcMap.put(0x2A,"slt");
        opcodeMap.put(0x23,"lw");
        opcodeMap.put(0x2B,"sw");
        opcodeMap.put(0x04,"beq");
        opcodeMap.put(0x05,"bne");

    }

    public void disassemble(int instruction){

        opcode = (instruction & opcodeMask) >>> 26;
        sourceRegA = (instruction & sourceRegAMask) >>> 21;
        sourceRegB = (instruction & sourceRegBMask) >>> 16;
        if(opcode == 0){
            destReg = (instruction & destRegMask) >>> 11;
            func = (instruction & funcMask);
            System.out.printf("%H %s $%d,$%d,$%d",beginingAddress,funcMap.get(func),destReg,sourceRegA,sourceRegB);
        }else{
            offset = (short)(instruction & offsetMask);
            if(opcode == 0x23 || opcode == 0x2B){
                System.out.printf("%H %s $%d,%d($%d)",beginingAddress,opcodeMap.get(opcode),sourceRegB,offset,sourceRegA);
            }else{
                destAddress = beginingAddress + (offset << 2) + 4;
                System.out.printf("%H %s $%d,$%d,address %H",beginingAddress,opcodeMap.get(opcode),sourceRegA,sourceRegB,destAddress);
            }
        }
    }

    public void disassembleAll(){
        for (Integer integer : instructionList) {
            disassemble(integer);
            beginingAddress += 4;
            System.out.println();
        }




    }

    public static void main(String[] args) {
        MipsDisassembler md = new MipsDisassembler();
        md.disassembleAll();
    }

}
