package bunchbysoh;

public class Main {
  static class CountsBySoH {
    public int healthy = 0;
    public int exchange = 0;
    public int failed = 0;
  };

  static CountsBySoH countBatteriesByHealth(int[] presentCapacities) {
    CountsBySoH counts = new CountsBySoH();
    for(int presentCapacity:presentCapacities)
      {
        double SoH = 100 * presentCapacity/120.0;
        if(SoH>83 && SoH<=100)
           counts.healthy++;
        else if(SoH>=63 && SoH<=83)
           counts.exchange++;
        else if(SoH<63)
          counts.failed++;
      }
    return counts;
  }

  static void testBucketingByHealth() {
    System.out.println("Counting batteries by SoH...\n");
    int[] presentCapacities = {113, 116, 80, 95, 92, 70};
    CountsBySoH counts = countBatteriesByHealth(presentCapacities);
    assert(counts.healthy == 2);
    assert(counts.exchange == 3);
    assert(counts.failed == 1);
    int[] allHealthy = {120,120,120,120,120,120};
    CountsBySoH allHealthycounts = countBatteriesByHealth(allHealthy);
    assert(allHealthycounts.healthy == 6);
    assert(allHealthycounts.exchange == 0);
    assert(allHealthycounts.failed == 0);
    
    int[] allFailed = {0,50,10,22,40,6,17,11,32};
    CountsBySoH allFailedcounts = countBatteriesByHealth(allFailed);
    assert(allFailedcounts.healthy == 0);
    assert(allFailedcounts.exchange == 0);
    assert(allFailedcounts.failed == 9);

    int[] allBoundary = {0,75,76,99,100};
    CountsBySoH allBoundarycounts = countBatteriesByHealth(allBoundary);
    assert(allBoundarycounts.healthy == 1);
    assert(allBoundarycounts.exchange == 2);
    assert(allBoundarycounts.failed == 2);
    System.out.println("Done counting :)\n");
  }

  public static void main(String[] args) {
    testBucketingByHealth();
  }
}
