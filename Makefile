run: compile
	java -cp . Frontend
	
compile: Frontend.class

Frontend.class: Frontend.java Backend.class BackendInterface.class Airport.class AirportInterface.class AirportReader.class AirportReaderInterface.class CS400Graph.class
	javac -cp .:junit5.jar Frontend.java
	
FrontendTests.class: FrontendTests.java
	javac -cp .:junit5.jar FrontendTests.java
	
AirportTests.class: AirportTests.java
	javac -cp .:junit5.jar AirportTests.java
	
Backend.class: Backend.java
	javac Backend.java
	
BackendInterface.class: BackendInterface.java
	javac BackendInterface.java
	
Airport.class: Airport.java
	javac Airport.java
	
AirportInterface.class: AirportInterface.java
	javac AirportInterface.java
	
AirportReader.class: AirportReader.java
	javac AirportReader.java
	
AirportReaderInterface.class: AirportReaderInterface.java
	javac AirportReaderInterface.java
	
CS400Graph.class: CS400Graph.java
	javac CS400Graph.java
	
test: testData FrontendTests
	
FrontendTests: FrontendTests.class
	java -jar junit5.jar -cp . --scan-classpath -n FrontendTests
	
testData: AirportTests.class
	java -jar junit5.jar -cp . --scan-classpath -n AirportTests
	
clean:
	$(RM) *.class
