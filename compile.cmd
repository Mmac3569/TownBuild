cd..
cd Documents
cd TownBuild
cd "ve vyvoji"
del TownBuild.jar
javac -d . TownBuild.java
javac -d . TownBuildGUI.java
jar -cvmf TownBuild.mf TownBuild.jar main/TownBuild.class main/Background.class main/Rendering.class main/Odpocet.class GUI/TownBuildGUI.class