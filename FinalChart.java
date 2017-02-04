
/* 
 * FinalChart.java 
 * 
 * 
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;

/**
 * This FinalChart class generates a graph with correlation between Total
 * earnings, Total expenses, Healthy vs Risky expenses, Relationship struggle
 * info of users.
 * 
 * @author Vinay Vasant More
 *
 */
public class FinalChart {

	static BufferedReader br1;
	static ArrayList<User> userList = new ArrayList<User>();

	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String args[]) throws IOException {
		FinalChart ft = new FinalChart();

		// Selecting dataset directory by manual selection
		JFileChooser select = new JFileChooser();
		select.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		select.showSaveDialog(null);
		File fd = select.getSelectedFile();
		String visulationString = ft.extractTransactions(fd);
		ft.createHTMLReport(visulationString);
	}

	/**
	 * createHTMLReport function generates html file with pie chart for each
	 * user file
	 *
	 * @param visualizationString
	 *            String which stores visualizations data for report
	 * 
	 * @return void method
	 * 
	 */
	void createHTMLReport(String visualizationString) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("FinalChart.html"));
			//Reference: Used Google Chart below - API https://developers.google.com/chart/
			bw.write(
					"<html><head><script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script><script type=\"text/javascript\">"
							+ "google.charts.load('current', {'packages':['corechart']});google.charts.setOnLoadCallback(drawSeriesChart);function drawSeriesChart(){var data ="
							+ visualizationString
							+ "var options = {title: 'Correlation between Total earnings, Total expenses, Healthy vs Risky expenses, Relationship struggle info of users..... Blue color = No Relationship struggle, Red Color = Relationship Struggle, Bubble size = Total Expenses',hAxis: {title: 'Total Earnings in $',textStyle: {fontSize: 18}},vAxis: {title: '(Healty - risky) expenses in $',textStyle: {fontSize: 18}},bubble: {textStyle: {fontSize: 10}}};"
							+ "var chart = new google.visualization.BubbleChart(document.getElementById('series_chart_div'));chart.draw(data, options);}</script></head><body><div id=\"series_chart_div\" style=\"width: 1500px; height: 800px;\"></div></body></html>");
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * extractTransactions function extracts necessary information from user
	 * transactions file
	 *
	 * @param fd
	 *            File directory where all user transaction files are stored
	 * 
	 * @return String: returns String which stores visualization data
	 * 
	 */
	String extractTransactions(File fd) throws IOException {
		// TODO Auto-generated method stub
		int userCounter = 0;
		File[] files = fd.listFiles();
		for (File f : files) {
			userList.add(new User());
			br1 = new BufferedReader(new FileReader(f));
			String str1 = br1.readLine();

			while ((str1 = br1.readLine()) != null) {
				int counter = 0;
				userList.get(userCounter).USERID = Integer.parseInt(str1.substring(0, str1.indexOf(',')));
				for (int i = 0; i < str1.length(); i++) {
					if (str1.charAt(i) == ',' && counter < 2) {
						counter = counter + 1;
					}
					if (counter == 2) {
						int j = str1.indexOf(',', i + 1);
						String tempString = str1.substring(i + 1, j);
						double tempCount = Double.parseDouble(str1.substring(j + 1, str1.indexOf(',', j + 1)));
						if (tempString.toLowerCase().contains("paycheck")) {
							userList.get(userCounter).totalEarnings = userList.get(userCounter).totalEarnings
									+ tempCount;
						} else {
							userList.get(userCounter).totalExpenses = userList.get(userCounter).totalExpenses
									+ tempCount;
							if (tempString.toLowerCase().contains("club") || tempString.toLowerCase().contains("bar")
									|| tempString.toLowerCase().contains("brewery")
									|| tempString.toLowerCase().contains("wine")
									|| tempString.toLowerCase().contains("club")
									|| tempString.toLowerCase().contains("nba")
									|| tempString.toLowerCase().contains("ticket")
									|| tempString.toLowerCase().contains("bowling"))
								userList.get(userCounter).riskyExpenses = userList.get(userCounter).riskyExpenses
										+ tempCount;
							else if (tempString.toLowerCase().contains("music")
									|| tempString.toLowerCase().contains("art")
									|| tempString.toLowerCase().contains("guitar")
									|| tempString.toLowerCase().contains("piano")
									|| tempString.toLowerCase().contains("craft")
									|| tempString.toLowerCase().contains("museum")
									|| tempString.toLowerCase().contains("book")
									|| tempString.toLowerCase().contains("library")
									|| tempString.toLowerCase().contains("geek")
									|| tempString.toLowerCase().contains("education"))
								userList.get(userCounter).healthyExpenses = userList.get(userCounter).healthyExpenses
										+ tempCount;
						}
						if (tempString.contains("Divorce")) {
							userList.get(userCounter).struggle = "Yes";
						}
						break;
					}

				}
			}
			userCounter = userCounter + 1;
		}
		String result = "google.visualization.arrayToDataTable([['USERID', 'Total Earnings', '(Healthy-risky) Expenses','Relationship Struggle','Total Expenses'],";
		for (int k = 0; k < userList.size(); k++) {
			result = result + "['" + userList.get(k).USERID + "', " + userList.get(k).totalEarnings + ", "
					+ ((userList.get(k).healthyExpenses * -1) - (userList.get(k).riskyExpenses * -1)) + ", '"
					+ userList.get(k).struggle + "', " + userList.get(k).totalExpenses * -1 + "],";
		}
		result = result.substring(0, result.length() - 1) + "]);";
		System.out.println(result);
		return result;
	}

}
