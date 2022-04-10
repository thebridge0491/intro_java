#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** DocComment:
 * <p>Introduction, basic syntax/features.</p> */
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
				org.yaml.snakeyaml.Yaml yaml = new org.yaml.snakeyaml.Yaml();
				//java.util.Map<String, Object> yamlmap = yaml.loadAs(
				//	data_str, java.util.<String, Object>HashMap.class);
				java.util.Map<String, Object> yamlmap = yaml.load(data_str);
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

	private static void run_${name}(String progname, String name) {
	    java.util.regex.Pattern re = java.util.regex.Pattern.compile(
        //	"quit", java.util.regex.Pattern.CASE_INSENSITIVE);
        	"(?i)quit");
        java.util.regex.Matcher m = re.matcher(name);
        System.out.format("%s match: %s to %s\n",
        	m.matches() ? "Good" : "Does not", name, re.pattern());
	}

    private static void printUsage(String str, int status) {
        System.err.format("Usage: java %s [-h][-u name]\n",
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
                case 'h': printUsage("", 0);
                    break;
                case 'u':
                    if ((size <= i + 1) || ('-' == args[i + 1].charAt(0)))
                        printUsage("Missing argument for " + option, 1);
                    optsMap.put("name", args[++i]);
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
		    { put("name", "World"); }
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


    	java.util.Map<String, Object> json_cfg = null;
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
        }

    	@SuppressWarnings("unchecked")
    	String[][] tup_arr = {
			{ini_cfg.toString(), ini_cfg.get("default").get("domain"),
				ini_cfg.get("user1").get("name")},
			/*{json_cfg.toString(), ((javax.json.JsonString)json_cfg.get("domain")).toString(),
				((javax.json.JsonString)((Map<String, Object>)json_cfg.get("user1")).get("name")).toString()},*/
			{json_cfg.toString(), (String)json_cfg.get("domain"),
				(String)((Map<String, Object>)json_cfg.get("user1")).get("name")},
			{toml_cfg.toString(), (String)toml_cfg.get("domain"),
				(String)((Map<String, Object>)toml_cfg.get("user1")).get("name")},
			{yaml_cfg.toString(), (String)yaml_cfg.get("domain"),
				(String)((Map<String, Object>)yaml_cfg.get("user1")).get("name")}
		};

        //System.out.format("ini4j config start section: %s\n",
    	//	ini_cfg.keySet().iterator().next());
    	for (int i = 0; tup_arr.length > i; ++i) {
			System.out.format("config: %s\n", tup_arr[i][0]);
			System.out.format("domain: %s\n", tup_arr[i][1]);
			System.out.format("user1Name: %s\n\n", tup_arr[i][2]);
		}

    	run_${name}(Main.class.getName(), optsMap.get("name"));

	    rootLogger.debug("exiting main()");
    }
}
