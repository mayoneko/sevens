import java.util.*;
import sevens.*;

void setup() {
  List<Algorithm> algorithms=Arrays.asList(new MayonekoAlgorithm(), new KidsAlgorithm(), new GreatAlgorithm());
  List<Integer> winingRate=Arrays.asList(0, 0, 0);
  for (int i=0; i<10000; i++) {
    Map<Integer, Integer> result=new Game(algorithms).run();
    winingRate.set(result.get(1), winingRate.get(result.get(1))+1);
  }

  println(winingRate);
}

void draw() {
  exit();
}
