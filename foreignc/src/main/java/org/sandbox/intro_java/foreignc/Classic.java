package org.sandbox.intro_java.foreignc;

public class Classic {
    static {
        //for (String pathX : System.getProperty("java.library.path", 
        // 		".:/usr/local/lib").split(":"))
		//	com.sun.jna.NativeLibrary.addSearchPath("intro_c-practice", pathX);
		System.setProperty("jna.library.path", 
			System.getProperty("jna.library.path", 
			System.getProperty("java.library.path", ".:/usr/local/lib")));
    }
    
	private final static IClassic_c Classic = IClassic_c.Classic;
    
	public static long fact_i(long n) {
		return Classic.fact_i(n);
	}
	public static long fact_lp(long n) {
		return Classic.fact_lp(n);
	}

	public static float expt_i(float b, float n) {
		return Classic.expt_i(b, n);
	}
	public static float expt_lp(float b, float n) {
		return Classic.expt_lp(b, n);
	}
	
	public static void main(String[] args) {
		System.out.format("fact(%d): %d\n", 5, fact_i(5));
	}
}
