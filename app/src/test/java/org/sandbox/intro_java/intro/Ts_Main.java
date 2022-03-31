package org.sandbox.intro_java.intro;

@org.junit.platform.suite.api.Suite
@org.junit.platform.suite.api.IncludeClassNamePatterns({"^.*Test$",
  "^Test.*$", "^.*Prop$", "^Prop.*$"})
@org.junit.platform.suite.api.SelectClasses({CollectionsTest.class})
public class Ts_Main {
    public static void main(String[] args) {
        if (1 > args.length)
            org.junit.platform.console.ConsoleLauncher.main("-c", 
                CollectionsTest.class.getName());
        else 
            org.junit.platform.console.ConsoleLauncher.main(args);
    }
}
