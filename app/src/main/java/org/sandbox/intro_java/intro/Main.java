/** DocComment:
 * <p>Introduction, basic syntax/features.</p> */
package org.sandbox.intro_java.intro;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.sandbox.intro_java.util.Library;
import org.sandbox.intro_java.practice.*;

enum ConstItems {
    ZERO(0), NUMZ(26);
    private int numVal;
    
    ConstItems(int numVal) { this.numVal = numVal;
    }
    
    public int getNumVal() { return this.numVal;
    }
}

public class Main {
	static {
		// from cmdln: java -Dlog4j.configurationFile=path/log4j2.xml ...
		//System.setProperty("log4j.configurationFile", "log4j2.xml");
		// from cmdln: java -Dlogback.configurationFile=path/logback.xml ...
		System.setProperty("logback.configurationFile", "logback.xml");
	}
	private static final Logger rootLogger = LoggerFactory.getLogger(
		Main.class.getName());
	
	private static java.util.Map<String, Object> deserialize_str(
			String data_str, String fmt) {
		java.util.Map<String, Object> blank_cfg = 
			new java.util.HashMap<String, Object>();
		blank_cfg.put("fmt", fmt);
		switch (fmt) {
			case "json":
			case "yaml":
				org.snakeyaml.engine.v2.api.LoadSettings settings = 
				    org.snakeyaml.engine.v2.api.LoadSettings.builder().build();
				org.snakeyaml.engine.v2.api.Load yaml = 
				    new org.snakeyaml.engine.v2.api.Load(settings);
				java.util.Map<String, Object> yamlmap =
				    (java.util.Map<String, Object>)yaml.loadFromString(data_str);
				blank_cfg.putAll(yamlmap);
				break;
			case "toml":
				com.moandjiezana.toml.Toml toml = 
					new com.moandjiezana.toml.Toml();
				java.util.Map<String, Object> tomlmap = 
					toml.read(data_str).toMap();
				blank_cfg.putAll(tomlmap);
				break;
			/*case "json":
				javax.json.JsonObject jsonobj = null;
                javax.json.JsonReader rdr = null;
                rdr = javax.json.Json.createReader(new java.io.StringReader(
                    data_str));
                jsonobj = rdr.readObject();
                
                java.util.Map<String, Object> jsonmap = 
					new java.util.HashMap<String, Object>();
				for (java.util.Map.Entry<String, javax.json.JsonValue> entryX: jsonobj.entrySet()) {
					if (jsonobj.getClass() != entryX.getValue().getClass())
						jsonmap.put(entryX.getKey(), entryX.getValue());
					else {
						javax.json.JsonObject jsonsub = (javax.json.JsonObject)entryX.getValue();
						java.util.Map<String, Object> jsonsubmap = 
							new java.util.HashMap<String, Object>();
						for (java.util.Map.Entry<String, javax.json.JsonValue> entrySub: jsonsub.entrySet()) {
							jsonsubmap.put(entrySub.getKey(), entrySub.getValue());
						}
						jsonmap.put(entryX.getKey(), jsonsubmap);
					}
				}
				rdr.close();
				blank_cfg.putAll(jsonmap);
				break;*/
			default:
				System.err.println("Unknown fmt " + fmt);
				//System.exit(1);
		}
		return blank_cfg;
	}
	
	private static void run_intro(String progname, String rsrc_path,
            String name, int num, boolean is_expt2) {
	    long timeIn_mSecs = System.currentTimeMillis();
	    
	    // basic datatypes
	    boolean isDone = false;
	    int numI = 0, arrLen = ConstItems.ZERO.getNumVal();
	    final int seedi = (int)timeIn_mSecs, delayMSecs = (int)2.5e3;
	    float timeDiff = 0.0f;
	    char ch = '\0';
	    
	    // string & arrays
	    final String noname = "World", greetFile = "greet.txt";
	    String greetStr, buf = "HELP";
	    char[] str1 = new char[64];
	    int[] numArr = {0b1001, 011, 0x9, 9};
	    
	    // composites
	    java.util.Random rnd = new java.util.Random(seedi);
	    num = (0 == num) ? (rnd.nextInt(18) + 2) : num;
	    User user1 = new User();	// new User(name, num);
	    user1.name_$eq(name);
	    user1.num_$eq(num);
	    user1.timeIn_$eq(timeIn_mSecs);
	    
	    arrLen = numArr.length;
	    
	    for (int ival : numArr)
	        numI += ival;
	    assert((arrLen * numArr[0]) == numI);
	    
	    ch = Intro.delay_char(delayMSecs);
	    
	    do {
	        short i_sh = -1;
	        long i_l = 1L;
	        double d1 = 100.0, d2 = 1.0e6;	        
	        
	        isDone = true;
	    } while(!isDone);
	    
	    java.util.regex.Pattern re = java.util.regex.Pattern.compile(
        //	"quit", java.util.regex.Pattern.CASE_INSENSITIVE);
        	"(?i)quit");
        java.util.regex.Matcher m = re.matcher(name);
        System.out.format("%s match: %s to %s\n",
        	m.matches() ? "Good" : "Does not", name, re.pattern());
	    
	    java.util.Date dt1 = new java.util.Date(user1.timeIn());
	    
	    greetStr = Intro.greeting(rsrc_path, greetFile, user1.name());
	    System.out.format("%s\n%s!\n", dt1.toString(), greetStr);
	    
	    timeDiff = (System.currentTimeMillis() - user1.timeIn()) / 1000.0f;
	    System.out.format("(program %s) Took %.1f seconds.\n", 
	    	progname, timeDiff);
	    System.out.println(String.format("%0" + 40 + "d", 0).replace("0", 
	            "-"));
	    
	    Integer[] ints = {2, 1, 0, 4, 3};
	    java.util.List<Integer> lst =
	    	new java.util.LinkedList<Integer>(Arrays.asList(ints));
	    
	    if (is_expt2) {
	    	System.out.format("expt(2.0, %.1f) = %.1f\n", 
				(float)user1.num(), Classic.expt_i(2.0f, (float)user1.num()));
	    	
	    	String res0 = Library.<Integer>mkString(Arrays.asList(ints));
	    	System.out.format("reverse(%s): ", res0);
	    	Integer[] ints_tmp = new Integer[ints.length];
			for (int i = 0; ints.length > i; ++i)
				ints_tmp[i] = ints[i];
	    	SequenceopsArray.<Integer>reverse_lp(ints_tmp);
	    	String res1 = Library.<Integer>mkString(Arrays.asList(ints_tmp));
	    	System.out.println(res1);
	    	
	    	System.out.format("Arrays.sort(%s): ", res0);
	    	Arrays.sort(ints);
	    	String res2 = Library.<Integer>mkString(Arrays.asList(ints));
	    	System.out.println(res2);
	    } else {
	    	System.out.format("fact(%d) = %d\n", user1.num(),
	    		Classic.fact_i(user1.num()));
	    	
	    	String res0 = Library.<Integer>mkString(lst);
	    	int el = 3;
	    	int idx = Sequenceops.<Integer>indexOf_lp(el, lst, Library.intCmp);
	    	System.out.format("indexOf(%d, %s, intCmp): %d\n", el, res0,
	    		idx);
	    	
	    	int new_val = 50;
	    	System.out.format("%s.add(%d): ", res0, new_val);
	    	lst.add(new_val);
	    	String res1 = Library.<Integer>mkString(lst);
	    	System.out.println(res1);
	    }
	    System.out.println(String.format("%0" + 40 + "d", 0).replace("0", 
            "-"));
	    int n_pascal = 5;
	    int[][] pascal_arr = Classic.pascaltri_add(n_pascal);
	    System.out.format("pascaltri_add(n:%d) : \n", n_pascal);
	    Classic.printPascalTri(n_pascal, pascal_arr);
	    
	    System.out.println(String.format("%0" + 40 + "d", 0).replace("0", 
            "-"));
		int ndisks = 4;
		int len_hanoi = (int)Math.pow(2.0f, ndisks) - 1;
		int[][] hanoi_arr = ClassicPuzzles.hanoi(1, 2, 3, ndisks);
		System.out.format("hanoi(src:1, dest:2, spare:3, ndisks:%d) : \n", 
			ndisks);
		for (int i = 0; len_hanoi > i; ++i)
			System.out.format("move #%02d: move from %d to %d\n", i + 1,
				hanoi_arr[i][0], hanoi_arr[i][1]);
	    
	    System.out.println(String.format("%0" + 40 + "d", 0).replace("0", 
            "-"));
		int numqueens = 8, queensNdx = (int)(50.0f * rnd.nextFloat());
		int[] nqueens_arr = ClassicPuzzles.nqueens(queensNdx, numqueens);
		
		System.out.format("nqueens(ndx:%d, numqueens:%d) : \n", queensNdx,
			numqueens);
		System.out.print("{");
		for (int r = 0; numqueens > r; ++r)
			System.out.format("(%c, %d), ", 'a' + r, nqueens_arr[r]);
		System.out.println("}");
		
		for (int r = 0; numqueens > r; ++r, System.out.println()) {
			System.out.format("\'%d\'", numqueens - 1 - r);
			for (int c = 0; numqueens > c; ++c)
				System.out.format("\'%c\'", 
					((numqueens - 1 - r) == nqueens_arr[c]) ? 'Q' : '.');
		}
		System.out.print("\' \'");
		for (int c = 0; numqueens > c; ++c)
			System.out.format("\'%c\'", 'a' + c);
		System.out.print("\n\n");
	    
	    System.out.println(String.format("%0" + 40 + "d", 0).replace("0",
            "-"));
	    
	    Person pers = new Person("I.M. Computer", 32);
	    assert(pers instanceof Object); // org.sandbox.intro_java.intro.Person
	    try {
	        assert(pers.getClass() == Class.forName(
				"org.sandbox.intro_java.intro.Person"));
	    } catch (ClassNotFoundException exc) {
	        exc.printStackTrace();
	    }
	    System.out.format("%s\n", pers.toString());
	    pers.setAge(33);
	    System.out.format("pers.setAge(%d): %s\n", 33, pers.toString());
	}
	
    private static void printUsage(String str, int status) {
        System.err.format("Usage: java %s [-h][-u name][-n num][-2]\n",
			Main.class.getName());
        System.err.println(str);
        System.exit(status);
    }

	private static void parse_cmdopts(Map<String, String> optsMap, 
            String[] args) {
        String option = null;
		rootLogger.info("parse_cmdopts()");
        for (int i = 0, size = args.length; size > i; ++i) {
			option = args[i];
              
            if ('-' != option.charAt(0) || 1 == option.length())
                printUsage("Not an option: " + option, 1);
            switch (option.charAt(1)) {
                case 'u': 
                    if ((size <= i + 1) || ('-' == args[i + 1].charAt(0)))
                        printUsage("Missing argument for " + option, 1);
                    optsMap.put("name", args[++i]);
                    break;
                case 'n': 
                    if ((size <= i + 1) || ('-' == args[i + 1].charAt(0)))
                        printUsage("Missing argument for " + option, 1);
                    optsMap.put("num", args[++i]);
                    break;
                case '2': 
                    //if ((size <= i + 1) || ('-' == args[i + 1].charAt(0)))
                    //    printUsage("Missing argument for " + option, 1);
                    optsMap.put("is_expt2", "1");
                    break;
                case 'h': printUsage("", 0);
                    break;
                default: printUsage("Unknown option: " + option, 1);
          	}
		}
    }

    /** Brief description.
     * @param args - array of command-line arguments */
    public static void main(String[] args) {
		@SuppressWarnings("unchecked")
		Map<String, String> optsMap = new HashMap<String, String>() {
		    static final long serialVersionUID = 660L;
		    { put("name", "World"); put("num", "0"); put("is_expt2", ""); }
		};
        parse_cmdopts(optsMap, args);
	    
	    String rsrc_path = null != System.getenv("RSRC_PATH") ? 
			System.getenv("RSRC_PATH") :
			System.getProperty("rsrcPath", "src/main/resources");
        
        org.ini4j.Ini ini_cfg = new org.ini4j.Ini();
        try {
        	ini_cfg.load(new java.io.FileReader(rsrc_path + "/prac.conf"));
        } catch (java.io.IOException exc0) {
			System.out.format("(exc: %s) Bad env var RSRC_PATH: %s\n",
				exc0, rsrc_path);
            try {
                ini_cfg.load(Main.class.getResourceAsStream("/prac.conf"));
            } catch (java.io.IOException exc1) {
                exc0.printStackTrace();
                exc1.printStackTrace();
                System.exit(1);
            }
        }
        System.out.format("ini4j config start section: %s\n",
    		ini_cfg.keySet().iterator().next());
    	
    	
        /*java.util.Map<String, Object> json_cfg = null;
        java.util.Map<String, Object> toml_cfg = null;
        java.util.Map<String, Object> yaml_cfg = null;
        try {
        	json_cfg = deserialize_str(new String(new java.io.FileInputStream(
                rsrc_path + "/prac.json").readAllBytes()), "json");
            toml_cfg = deserialize_str(new String(new java.io.FileInputStream(
                rsrc_path + "/prac.toml").readAllBytes()), "toml");
            yaml_cfg = deserialize_str(new String(new java.io.FileInputStream(
                rsrc_path + "/prac.yaml").readAllBytes()), "yaml");
        } catch (java.io.IOException exc0) {
			try {
				json_cfg = deserialize_str(new String(
					Main.class.getResourceAsStream("/prac.json"
					).readAllBytes()), "json");
				toml_cfg = deserialize_str(new String(
					Main.class.getResourceAsStream("/prac.toml"
					).readAllBytes()), "toml");
				yaml_cfg = deserialize_str(new String(
					Main.class.getResourceAsStream("/prac.yaml"
					).readAllBytes()), "yaml");
			} catch (java.io.IOException exc1) {
				System.err.println("Error: " + exc1);
			}
        }*/
    	
    	@SuppressWarnings("unchecked")
    	String[][] tup_arr = {
			{ini_cfg.toString(), ini_cfg.get("default").get("domain"),
				ini_cfg.get("user1").get("name")},
			/*{json_cfg.toString(), ((javax.json.JsonString)json_cfg.get("domain")).toString(),
				((javax.json.JsonString)((Map<String, Object>)json_cfg.get("user1")).get("name")).toString()},*/
			/*{json_cfg.toString(), (String)json_cfg.get("domain"),
				(String)((Map<String, Object>)json_cfg.get("user1")).get("name")},
			{toml_cfg.toString(), (String)toml_cfg.get("domain"),
				(String)((Map<String, Object>)toml_cfg.get("user1")).get("name")},
			{yaml_cfg.toString(), (String)yaml_cfg.get("domain"),
				(String)((Map<String, Object>)yaml_cfg.get("user1")).get("name")}*/
		};
		
    	for (int i = 0; tup_arr.length > i; ++i) {
			System.out.format("config: %s\n", tup_arr[i][0]);
			System.out.format("domain: %s\n", tup_arr[i][1]);
			System.out.format("user1Name: %s\n\n", tup_arr[i][2]);
		}
    	
    	run_intro(Main.class.getName(), rsrc_path, optsMap.get("name"),
            Integer.parseInt(optsMap.get("num")), 
    		!optsMap.get("is_expt2").equals(""));
	    
	    rootLogger.debug("exiting main()");
    }
}
