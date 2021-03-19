# NPREmailSort 
Author: Shubham Kadam
For: NPR Engineer Interview

This is a very rudimentary email sorting program. It takes in arguments as text files which include a list of emails.
It will read the text file(s), and then validate all the emails using the Apache Commons Email Validator. 
After it validates the emails, it will sort the emails by domain name, and then sort them alphabetically within those domains. 

I opted for maybe more encapsulation than was needed to perform a rather simple task. 

## Technologies Required
1. A minimum of Java 8 is required to run this program. You can download the latest version of Java at https://www.java.com/en/
2. A minimum of Gradle 4.9 will be required to run and build the program. You can download the latest version of Gradle at https://gradle.org/install/

## Limitations

The program uses the Apache Commons Email Validator  to validate email addresses. 
The Apache Email Validator adheres to RFC 2822 standards, if I am not mistaken.
Unless there are specific business requirements on what kinds of emails to accept, it is best to adhere to RFC standards. 

## How to Run

To build the program, navigate to the home directory of the program in terminal and run:

    gradle build

To run the program:

    gradle run --args="<path to file>"

I recommend putting your text files into the testFiles directory in the home directory. 

The program is also capable of taking multiple text files

    gradle run --args="<path to file1> <path to file2> ..."

You should see output.txt files in the home directory of the program.

## Improvements for Future Iterations

1.  Unit Testing could be improved. At the moment only the Validator Service and the Sort Service have dedicated unit tests.
    This could be expanded to include testing for the File Service as well as the File Handler. Tests to ensure that the correct
    Exceptions are thrown in certain circumstances should also be added.
2.  In the case that there are specific business requirements which set the standard for what emails are accepted, the current
    code should be adapted to fit those standards. At the moment, the current code uses the Apache Commons Email Validator, which adheres to RFC 2822 standards.
3.  In the future the tool can be expanded to not just be a CLI tool. If we add a simple UI and a download/upload function,
    this tool can be expanded to be a web tool that users can access easily. 
4.  If the tool is to be scaled and used across multiple platforms and users, containerization should strongly be considered.
