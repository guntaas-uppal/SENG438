>   **SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#1 – Introduction to Testing and Defect Tracking**

| Group: Group Number 7 |
|-----------------|
|Guntaas Singh Uppal |   
|Jindjeet Singh Cheema |   
|Rizam Goyal |   
|Sukhansh Singh Nagi |   
|Tahil Goyal | 


**Table of Contents**

(When you finish writing, update the following list using right click, then
“Update Field”)

[1 Introduction	1](#_Toc439194677)

[2 High-level description of the exploratory testing plan	1](#_Toc439194678)

[3 Comparison of exploratory and manual functional testing	1](#_Toc439194679)

[4 Notes and discussion of the peer reviews of defect reports	1](#_Toc439194680)

[5 How the pair testing was managed and team work/effort was
divided	1](#_Toc439194681)

[6 Difficulties encountered, challenges overcome, and lessons
learned	1](#_Toc439194682)

[7 Comments/feedback on the lab and lab document itself	1](#_Toc439194683)

# Introduction

Before undertaking this lab, our understanding of software testing was primarily grounded in our theoretical knowledge of software testing concepts, as covered in lectures and in our previous studies. Although we understood the overall objective of software testing, such as testing for functionality and detecting faults, our knowledge of software testing was limited in terms of practical experience in applying various software testing strategies in an organized and systematic way. Although exploratory testing was understood as an ad-hoc testing process, manual functional testing was understood as an organized process of executing test cases.

This lab has given us practical experience in exploratory testing, manual scripted testing, and regression testing on a real-world system under test, which is an ATM simulation. We have also learned how to formally record software defects using an industrial defect tracking tool.

# High-level description of the exploratory testing plan

The objective of our exploratory testing was to find out the defects in the ATM system without relying on any pre-defined test cases. Before starting our exploratory testing, we developed a high-level plan to facilitate our exploratory testing while still being flexible enough to accommodate changes.

The exploratory testing was done for the following aspects:

-System start-up and shut-down

-Card insertion and authentication with valid as well as invalid card numbers and PINs

-Core transaction-related functionality such as withdrawal, deposit, transfer, and balance inquiry

-Cancel functionality for various stages of transaction

Input value validation, including zero, negative, and unusual input values

We followed a breadth-first strategy, initially exploring all features to get a broad overview of the system under test. Once we detected unusual and wrong behavior, we concentrated more on these aspects to explore these unusual conditions. Test ideas were generated dynamically based on system responses, error messages, and unusual user interface behavior. This strategy helped us find defects related to validation, sessions, and system robustness, which were not explicitly tested by traditional scripted test cases.

# Comparison of exploratory and manual functional testing

Exploratory testing and manual functional testing have been used in an appropriate manner in this lab.

**Exploratory Testing:**
Exploratory testing has been highly effective in detecting unexpected defects. This testing style has been highly effective in detecting defects due to invalid input values, inconsistent validation, session hangs, and incorrect states of the system. However, it is a highly unstructured testing style, which can be highly difficult to reproduce without proper documentation.

**Manual Functional Testing:**
Manual scripted testing, as used in this lab with the help of a given test suite, has been highly effective in testing the specified requirements of an ATM system. This testing style has been highly effective in testing whether the functionalities of an ATM are working as expected. This testing style has also been highly effective in detecting any deviations in functionality from the specified requirements. However, it can be highly difficult to reproduce this testing style if any defect occurs in an area other than the specified requirements.

Overall, exploratory testing has been highly effective in detecting robustness and usability-related defects, while manual functional testing has been highly effective in detecting compliance-related defects.


# Notes and discussion of the peer reviews of defect reports

Once the testing was done, the reports created by the pairs were reviewed by other members of the group for defects. In reviewing these reports, we were particularly interested in ensuring the clarity and completeness of the information in the reports, including the reproduction steps, expected results, and actual results.

The peer review feedback resulted in several improvements, including the clarity of initial system states, reproduction steps, and the use of consistent terms in the reports. This resulted in standardization and enhanced quality and readability of the defects reported by the group.

# How the pair testing was managed and team work/effort was divided 

In pair testing, one team member acted as a driver by operating the ATM system, while the other acted as an observer by analyzing the system and identifying defects. This process was repeated by switching between each other to ensure equal contribution and understanding.

Exploratory testing was carried out in pairs, where each pair worked separately to identify and report defects in the system. Manual scripted testing and regression testing were carried out jointly by the team members, where each team member contributed to testing each defect in the system.

# Difficulties encountered, challenges overcome, and lessons learned

One of the biggest challenges faced was differentiating between expected limitations and defects in the system, especially when the behavior of the system was not specified. Another challenge faced in managing defect versions in multiple releases was ensuring that defects were not duplicated and that tracking was done correctly in regression testing.

From this lab, we have come to understand the significance of documentation, especially when it comes to defects that must be reproducible in nature. We have also come to understand the significance of various testing techniques and how regression testing is important in ensuring that defects are fixed and newly introduced defects are identified.

# Comments/feedback on the lab and lab document itself
Overall, this lab was a valuable and practical introduction to the process of software testing and defect tracking. The ATM simulation system was a complex enough system to reveal meaningful defects, yet simple enough to be effectively tested by hand. The instructions for the lab were generally well-written and well-organized, and the use of an industry-standard defect tracking tool helped to simulate the process of real-world software testing. This lab effectively reviewed the process of software testing, which will certainly prove to be a valuable experience for future software engineering projects.

One logistical issue faced by the class with regards to this lab had to do with collaboration for the lab report. The lab report was initially drafted collaboratively by all members of the group, working on a word processing document to allow all members to contribute to the report at the same time, with real-time review capabilities for all members. Once the report had been finalized, it was converted to Markdown format for submission on GitHub. This meant that not all members were able to make direct contributions to the report on the GitHub repository, though all members were very involved in the production of the report.

