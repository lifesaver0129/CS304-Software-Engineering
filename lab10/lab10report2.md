# Software Engineering Lab#10

### 11510225 Yuxing Hu

---

## Assignment 2: A short essay to analyze mutation testing

### Keywords

current state, pros, cons, compare to others

### Introduction

Test case design is the core problem in software testing. In the process of practice, the design of test cases is based on the test adequacy criterion, such as business process coverage, data flow coverage and statement coverage. The mutation test provides a measure of the adequacy of the test based on the defect, and evaluates and improves the adequacy of the test case set.

### Definition

Mutation testing (called program mutation in the specification, which is used in the following two) is a error-driven test to find out how the software has been left with some minor errors after other tests have been done. This method is usually carried out dynamically with mutation Test tool, which belongs to dynamic test. The following is the principle of mutation testing to clarify its definition.

### Principle

First, specifically, the tester first designs the mutation operator according to the program characteristics, and the mutation operator usually makes minor changes to the program under the condition of the grammar. Then the mutation operator can be used to generate a large number of variants, after the equivalent variant is identified, if the existing test cases can not kill all the non-equivalence variants, additional design of new test cases and add to the test case set to improve the adequacy of the test.

In addition to the adequacy assessment that can be used for test case sets, mutation tests can be used to simulate the real flaws of the software being tested by using mutation defects, thus assisting in the evaluation of the effectiveness of the test methods proposed by the researchers. According to Andrew's research, the variation of mutation operator is similar to that of real defect in effectiveness evaluation. That's why the mutation test is based on faulty, error-driven testing.
 
In this process, there are two premises assumptions: Assumption 1 (Skilled Programmer's hypothesis) assumes that the skilled programmer is rich in programming experience, the written defect code is very close to the correct code, only need to make small amplitude code modification can complete the defect removal.

Based on this hypothesis, the mutation test can simulate the actual programming behavior of a skilled programmer only by making small-amplitude code modifications to the program being tested. Assumption 2 (Coupling effect hypothesis) if a test case can detect a simple defect, the test case is also easy to detect more complex defects.

Here the meaning of the simple flaw is only in the original program to perform a single grammatical modification of the formation of defects, and the meaning of complex defects in the original program in order to perform several times a single grammatical modification of the formation of defects. From these two hypotheses, it is not difficult to see that if there are obvious code errors in the program, the mutation test is not necessarily valid. So it is stated in the definition of the specification that "it is to find out some minor errors that have been made after other tests have been done by the software under test". And some small errors, no matter how many products, development, testing, architecture review, often is the test cases can not be covered. This is where mutation testing comes in.

### Application

Currently, the tools that can be applied to Java are: Mujava, Muclipse (both prototype tools for academic research, and tools that are not available for enterprise production development). However, in the use of this time due to the lack of Extendedoj.jar failed to run successfully. The use of the official documentation is also very unfriendly. Variation testing from theory to practical application, there is still a way (of course, there may be no way to go).

### Conclusion

Mutation testing, as a software-oriented testing technique, has been paid close attention to by academic researchers and has achieved a lot of research results. As a tester, look forward to its future production applications.

### Reference

[1] Jia, Y., & Harman, M. (2011). **An analysis and survey of the development of mutation testing**. *IEEE transactions on software engineering*, 37(5), 649-678.

[2] Howden, W. E. (1982). **Weak mutation testing and completeness of test sets**. *IEEE Transactions on Software Engineering*, (4), 371-379.

[3] Offutt, A. J., & Pan, J. (1997). **Automatically detecting equivalent mutants and infeasible paths**. *Software testing*, verification and reliability, 7(3), 165-192.
