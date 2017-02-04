# intuit_rit_challenge
intuit-recruiting/rit-challenge
--------------------------------------------------------------------------------------
Files in this directory:

1. finalchart.jar: executable file which will generate final chart report
2. individualreports.jar: executable file which generates individual html reports
3. rit-challenge-master.zip: dataset of 100 users
4. FinalChart.java: Source file for finalchart.jar
5. User.java: represents user class
6. VendorList.java: Source file for individualreports.jar
7. WriteUpBonus.txt:  write up of the rationale used to determine the measure to determine people’s compatibility
			
How to run the program:

	1. Download finalchart.jar, individualreports.jar and transaction-data.zip files
	2. Unzip rit-challenge-master.zip file
	3. Run the finalchart.jar file
	4. Select particular file directory in which user files are extracted
	5. Check for FinalChart.html file which shows correlations at same location where .jar files are kept
	6. Run individualreports.jar file
	7. Select particular file directory in which user files are extracted
	8. Check for individual html reports at same location where .jar files are kept

Rationale used to determine the measure for people’s compatibility:

Users who spend money on healthy activities than unhealthy activities are more likely to have
good compatibility with their partner hence, less relationship struggle.
Also, total earnings vs total expenses comparison to get additional information which will help in
analyzing the trend. I used this rationale collectively to determine the people’s compatibility.

Steps:

1. Extracted all information for a specific user
2. Calculated Total Earnings, Total Expenses, Total risky expenses, Total healthy expenses, and relationship struggle information
3. Undesirable amount of expenses at bars, clubs, etc. comes under risky expenses which can cause affect relationship in bad way
4. Healthy Expenses - Expenses which are made in some activities which actually helps in increasing the compatibility between partners
5. Relationship Struggle - Based on expenses made at divorce lawyer fees, etc. which actually shows user is undergoing a relationship struggle
6. Calculated (Healthy-Risky) expenses amount
7. Plotted that against Total earnings using Google chart

Interpretations from FinalChart.html

1. Red colored bubble shows user is undergoing relationship struggle - We can see from the final chart that all these users have low (Healthy-Risky) expense value i.e. they are spending a lot in undesirable activities than healthy activities which strenghten the relationship
2. Blue colored bubble shows user is not undergoing relationship struggle - We can see from the final chart that all these users have high (Healthy-Risky) expense value i.e. they are spending more on healthy activities which strenghten the relationship that undesirable ones
3. Bubble size shows total expenses value for a user. People with more expenses than their earnings are also more likely to struggle in a relationship, but there are some outliers here in provided data.

Suggestions:

1. People should spend on the healthy actvities like art, craft, painting, music, visiting museums, playing instruments, etc. 
This helps in bonding and strenghtening the relationship.
2. Unhealthy lifestyle like visiting clubs, bars on regular basis and spending a lot of money can affect the relationship.
3. Anazlye the earnings vs expenses data on monthly basis to alert yourself about your recent spendings and lifestyle.

