package test.StringCharacterIteratorTest;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class StringCharacterIteratorTest01 {
    public static void main(String[] args) {
        
        CharacterIterator it = new StringCharacterIterator("中国China");
        
        // 顺序遍历字符串"中国China"
        for(char c = it.first(); c != CharacterIterator.DONE; c = it.next()) {
            System.out.print(c + " ");
        }
        System.out.println();
        
        System.out.println("-----------------------------------------------------------");
        
        // 逆序遍历字符串"中国China"
        for(char c = it.last(); c != CharacterIterator.DONE; c = it.previous()) {
            System.out.print(c + " ");
        }
        System.out.println();
    }
}
