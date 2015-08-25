/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.fxml.util;

/**
 *
 * @author MOISES
 */
public class SystemInfo {
    static int mb = 1024*1024;
    private static Runtime instance = Runtime.getRuntime();
    public static Long getTotalMemory(){
        return instance.totalMemory();
    }
    public static Long getFreeMomory(){
        return instance.freeMemory();
    }
    public static Long getUsedMemory(){
       return getTotalMemory()-getFreeMomory();
    }
    public static long getMaxMemory(){
        return instance.maxMemory();
    }
    
    public static int getTotalMemoryInMB(){
           return (int) (getTotalMemory()/mb);
    }
    
    public static int getFreeMomoryInMB(){
        return (int) (getFreeMomory()/mb);
    }
    public static int getUsedMemoryInMB(){
       return (int) (getTotalMemory()-getFreeMomory())/mb;
    }
    public static int getMaxMemoryInMB(){
        return (int) (getMaxMemory()/mb);
    }
    
    public static float getUsedMemoryInPercent(){
        return (float) getUsedMemory()/getTotalMemory();
    }
}
