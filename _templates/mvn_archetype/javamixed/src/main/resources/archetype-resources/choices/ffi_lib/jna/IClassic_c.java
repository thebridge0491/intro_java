#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import com.sun.jna.Library;
import com.sun.jna.Native;

/** DocComment:
 * <p>Brief description.</p> */
public interface IClassic_c extends com.sun.jna.Library {
	// env LD_LIBRARY_PATH=.:/usr/local/lib
    // or
	/* // -D[java | jna].library.path=".:/usr/local/lib"
	static { // inside class file
        //for (String pathX : System.getProperty("java.library.path",
        // 		".:/usr/local/lib").split(":"))
		//	com.sun.jna.NativeLibrary.addSearchPath("intro_c-practice", pathX);
		System.setProperty("jna.library.path",
			System.getProperty("jna.library.path",
			System.getProperty("java.library.path", ".:/usr/local/lib")));
    }*/
    public final static IClassic_c Classic = Native.load("intro_c-practice",
  		IClassic_c.class);

    public long fact_i(long n);
    public long fact_lp(long n);

    public float expt_i(float b, float n);
    public float expt_lp(float b, float n);
}
