// "Replace with findFirst()" "true"

import java.util.List;

public class Main {
  public int testPrimitiveMap(List<String> data) {
    for(String str : dat<caret>a) {
      if(str.startsWith("xyz")) {
        int len = str.length();
        if(len > 10) {
          return len;
        }
      }
    }
    return 0;
  }
}