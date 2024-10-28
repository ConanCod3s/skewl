public class Quiz_10_7 {
    public static void main(String[] args) {
        String s = "123";

        // Method 1: Using Integer.parseInt(s)
        int i1 = Integer.parseInt(s);
        System.out.println("Using Integer.parseInt: " + i1);

        // Method 2: Using Integer.valueOf(s).intValue()
        int i2 = Integer.valueOf(s).intValue();
        System.out.println("Using Integer.valueOf(s).intValue(): " + i2);

        // Method 3: Using Double.parseDouble(s) cast to int
        int i3 = (int)(Double.parseDouble(s));
        System.out.println("Using (int)(Double.parseDouble(s)): " + i3);

        // Method 3: Using Integer.valueOf(s) to int
        int i4 = Integer.valueOf(s);
        System.out.println("Using Integer.valueOf(s): " + i4);

//        // Method 4: Using new Integer(s).intValue()
//        int i4 = (new Integer(s)).intValue();
//        System.out.println("Using new Integer(s).intValue(): " + i4);
    }
}
