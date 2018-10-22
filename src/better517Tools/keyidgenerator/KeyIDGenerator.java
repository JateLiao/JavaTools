package better517Tools.keyidgenerator;

import com.better517na.javaKeyIdGenerator.KeyIDFactory;

/**
 * @Desc KeyIDGenerator
 * @ProjectName JavaTools
 * @Company com.lsj
 * @CreateTime 2018/10/22 18:12
 * @Author tianzhong
 */
public class KeyIDGenerator {
    
    /**
     * Main Method: 请开始你的操作.
     *
     */ 
    public static void main(String[] args) {
        KeyIDFactory factory = new KeyIDFactory();
        factory.setConfigPath("D:\\ACCESS\\nacfg.ini");
        for (int i = 0; i < 35; i++) {
            try {
                System.out.println(factory.NewKeyID("default", "default"));
            } catch (Exception ex) {
            
            }
        }
    }
}
