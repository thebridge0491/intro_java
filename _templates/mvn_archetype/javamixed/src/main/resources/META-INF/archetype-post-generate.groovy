import java.nio.file.*

println "archetypeGroupId:archetypeArtifactId:archetypeVersion = ${request.getArchetypeGroupId()}:${request.getArchetypeArtifactId()}:${request.getArchetypeVersion()}"
println "groupId:artifactId:version = ${groupId}:${artifactId}:${version}"

//println "System.env: " + System.env
//println "System.properties: " + System.properties
println "request.properties: " + request.properties
println "request.outputDirectory = ${request.outputDirectory}"
//binding.variables.sort().each{ v -> println v }

//----------------------------------------
def glob_files(String dir) {
	def items = []
	Paths.get(dir).toFile().eachFile() { items += it }
	return items
}

def run_cmd(String cmd, File dir) {
	def proc = cmd.execute(null, dir)
	proc.waitForProcessOutput((Appendable)System.out, System.err)
	if (0 != proc.exitValue()) {
		throw new Exception("Command '${cmd}' exited with code: ${proc.exitValue()}")
	}
}


/*
try {
	licenseName = licenseName
} catch (exc) {
	licenseName = 'Apache-2.0'
}
*/
license = binding.hasVariable('license') ? license : 'Apache-2.0'
readmeExt = binding.hasVariable('readmeExt') ? readmeExt : '.rst'
buildTool = binding.hasVariable('buildTool') ? buildTool : 'gradle'
testFrwk = binding.hasVariable('testFrwk') ? testFrwk : 'junit'
executable = binding.hasVariable('executable') ? executable : 'no'
ffiLib = binding.hasVariable('ffiLib') ? ffiLib : 'none'

def packageInPathFormat = package.replaceAll("\\.", "/")
def dirX = Paths.get("${request.outputDirectory}", "${artifactId}").toFile()

["mkdir -p build", "cp -fR choices build/", "rm -fr choices"
	, "mv -v _hgignore .hgignore", "mv -v _gitignore .gitignore"
	, "mv -v build/choices/_parent_init/_hgignore.lst build/choices/_parent_init/.hgignore"
	, "mv -v build/choices/_parent_init/_gitignore.lst build/choices/_parent_init/.gitignore"
	].each { 
	cmd -> run_cmd(cmd, dirX) }

run_cmd("cp -v build/choices/readme/README${readmeExt} README${readmeExt}",
	dirX)
run_cmd("cp -v build/choices/_parent_readme/README${readmeExt} build/choices/_parent_init/README${readmeExt}",
	dirX)

if (license in ['Apache-2.0', 'MIT', 'BSD-3-Clause', 'GPL-3.0+', 'ISC', 
		'Unlicense']) {
	run_cmd("cp -v build/choices/license/LICENSE_${license}.txt LICENSE.txt",
		dirX)
}

if (buildTool in ['gradle', 'maven', 'ant', 'rake', 'make', 'sbt']) {
	def files = glob_files("${dirX}/build/choices/build_tool/${buildTool}")
	run_cmd("cp -vR ${files.join(' ')} .", dirX)
} else { // default: gradle
	def files = glob_files("${dirX}/build/choices/build_tool/gradle")
	run_cmd("cp -vR ${files.join(' ')} .", dirX)
}

if (testFrwk in ['junit', 'testng']) {
	def files = glob_files("${dirX}/build/choices/testfrwk/${testFrwk}")
	run_cmd("cp -vR ${files.join(' ')} src/test/java/${packageInPathFormat}/",
		dirX)
} else { // default: junit
	def files = glob_files("${dirX}/build/choices/testfrwk/junit")
	run_cmd("cp -vR ${files.join(' ')} src/test/java/${packageInPathFormat}/",
		dirX)
}

if ('yes' != executable) {
    run_cmd("rm -vf src/main/scala/${packageInPathFormat}/Main.scala src/main/java/${packageInPathFormat}/Main.java",
		dirX)
}

if (ffiLib in ['jna', 'swig']) {
	def files = glob_files("${dirX}/build/choices/ffi_lib/${ffiLib}")
	run_cmd("cp -vR ${files.join(' ')} src/main/java/${packageInPathFormat}/",
		dirX)
}
/*
if (Files.exists(Paths.get("_templates"))) {
	run_cmd("mkdir -vp ../_templates/${request.getArchetypeArtifactId()}",
	 	dirX)
	def archetypeOrgPath = "${request.getArchetypeGroupId()}".replaceAll("\\.", "/")
	run_cmd("cp -vfR ${System.properties['user.home']}/.m2/repository/${archetypeOrgPath}/${request.getArchetypeArtifactId()}/${request.getArchetypeVersion()} ../_templates/${request.getArchetypeArtifactId()}/", dirX)
}*/
