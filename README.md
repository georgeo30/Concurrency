# Concurrency
Concurrent Programming: Falling Words.  Designing  a multithreaded Java program, ensuring both thread safety and sufficient concurrency for it to work well

Instructions for execution:



In the terminal: the user can type in make to compile all the .java files in the src folder and save them in the bin folder.

The make file commands are as follows



>>make : this compiles all .java files



>>make doc : to provide documentation for the .java files

  

 // Please note that the doc folder has already been created. If the user wished to test the make doc method, please delete the doc folder before running the command.



>>make clean : to clean out the folders.



>>make run : this will run the wordApp with 25 maximum words and 6 falling words at a time, It will use the built in dictionary.



>>make example_dict : this will run the wordApp with 25 maximum words and 6 falling words at a time, It will use the provided example_dict file.



**Please note that if you want to change the parameters the easiest way is to go to the make file and change the parameters on either the run or example_dict command. You can also change the file name if you wish. 



If you would rather do it manually without using a make file please look below.





If the user wishes to test out the java programs manually without using the make file then please follow the steps:



1) a) if the user wishes to use a custom dictionary then place the file that you would like to test in the bin folder.

   b) If you wish to use the built in dictionary then just type in a fake name for the input file.	

2) navigate yourself to the bin folder from terminal.

3) use the java command to execute and open the application (example shown below)



to run the program (example): java WordApp 25 8 example_dict.txt

		  :This means the maximum number of words to be caught or missed is 25

		  :The maximum number of words to fall at any time is 8

		  :And the dictionary to be used is example_dict.txt 





You can find my gitHub repo at https://github.com/georgeo30/Concurrency
