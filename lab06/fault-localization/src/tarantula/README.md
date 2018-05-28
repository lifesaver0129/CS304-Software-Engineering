Tarantula Java Implementation
----------------------------
The classes in the tarantula directory are used to take the information from the json file produced by tacoco and junit tests on the program and 
then use the information to caculate the supsciousness for the lines of a program using the tarantula technique. Refer to the documentation
in the classes for more information.

TarantulaSuspiciousnessCalculation
----------------------------------
Java code obtained from: http://www.ics.uci.edu/~jajones/Tarantula.html. The code itself is fairly well documented, the program 
requires several boolean arrays in order to be able to compute suspisciousness of statments the following arrays were used in the research

	- Coverage Matrix - B[] a two dimensional boolean array where the first dimension is indexed byt test case number
	  and the second deimanetion is indexed by the statement number, specifies which program elements
	  are executed by which test cases

	- Failing Test Cases - F[] boolean array indexed by test case number, true= ail, false=pass

	- Live Test Cases - L[] boolean array indexed by test case number sepcifies which test cases should be used, true=use false=ignore 

 	- Coverable Statments -C[] boolean array indexed by program element number, true=coverable false=uncoverable

Runner Class
------------
This program stores the results from a junit tests and holds an array of the test case numbers that fail. All
test headers should be as follows test1, test2, test3 ect. Otherwise the Runner will not work properly.

CovMatrixReader Class
---------------------
This program reads the json file produces by tacoco and parses the information into the boolean arrays needed by 
TarantulaSuspiciousnessCalculation. The covMatrixReader is used only to parse information from the compact version of the json
file, this is the default json format when running tacoco.

TarantulaMain Class
-------------------
The main class for the project. Requires two command line arguments
	-(1) absolute path to json file - This should be in the tacoco dir after running the scripts in fl-scripts
	-(2) the name of the test program - TestSuite (the class is in a package include the packace in the name ex: triangle.TestSuite)
