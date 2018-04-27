@echo on


mvn install:install-file -Dfile=Cilantrum-0.0.1-jar-with-dependencies.jar -DgroupId=indra.cilantrum -DartifactId=Cilantrum -Dversion=0.0.1 -Dpackaging=jar

pause