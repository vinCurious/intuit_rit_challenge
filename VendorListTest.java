
/* 
 * VendorListTest.java 
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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JFileChooser;

/**
 * This VendorListTest class generates individual html pie chart for each user
 * file
 * 
 * @author Vinay Vasant More
 *
 */
public class VendorListTest {
	static HashMap<String, Double> hp;
	static BufferedReader br1;

	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String args[]) throws IOException {
		hp = new HashMap<String, Double>();
		VendorListTest vt = new VendorListTest();

		// Setting newsgroup directory by manual selection
		JFileChooser select = new JFileChooser();
		select.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		select.showSaveDialog(null);
		File fd = select.getSelectedFile();
		vt.extractTransactions(fd);
	}

	/**
	 * createHTMLReport function generates html file with pie chart for each
	 * user file
	 *
	 * @param name
	 *            String form of userid which is used to name each html file
	 * @param vendString
	 *            String which stores visualization data
	 * @param earnings
	 *            Double variable to store earnings of a user
	 * @param expenses
	 *            Double variable to store expenses of a user
	 * @param div
	 *            String to store relationship struggle faced or not - Yes/No
	 * 
	 * 
	 * @return void method
	 * 
	 */
	static void createHTMLReport(String name, String vendString, double earnings, double expenses, String div) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(name + ".html"));
			bw.write("<html><head>"
					+ "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>"
					+ "<script type=\"text/javascript\">"
					+ "google.charts.load(\"current\", {packages:[\"corechart\"]});"
					+ "google.charts.setOnLoadCallback(drawChart);" + "function drawChart() {"
					+ "var data = google.visualization.arrayToDataTable([['Expenses', 'Percentage'],"
					+ vendString.substring(0, vendString.length() - 1) + "]);var options = {"
					+ "title: 'User statistics\t\tEarnings: " + earnings + "\t\tRelationship Struggle?-" + div
					+ "\t\tExpenses: " + Math.round(expenses) + "'," + "is3D: true,};"
					+ "var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));"
					+ "chart.draw(data, options);}" + "</script>" + "</head>" + "<body>"
					+ "<div id=\"piechart_3d\" style=\"width: 1100px; height: 700px;\"></div>" + "</body>" + "</html>");

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
	 * @return void method
	 * 
	 */
	void extractTransactions(File fd) throws IOException {
		// TODO Auto-generated method stub
		int count = 0;
		File[] files = fd.listFiles();
		for (File f : files) {
			double creditsCount = 0;
			double divCount = 0;
			br1 = new BufferedReader(new FileReader(f));
			String str1 = br1.readLine();
			HashMap<String, Double> hmp = new HashMap<String, Double>();
			String userid = f.getName();
			while ((str1 = br1.readLine()) != null) {
				int counter = 0;
				userid = str1.substring(0, str1.indexOf(','));
				for (int i = 0; i < str1.length(); i++) {
					if (str1.charAt(i) == ',' && counter < 2) {
						counter = counter + 1;
					}
					if (counter == 2) {
						int j = str1.indexOf(',', i + 1);
						String tempString = str1.substring(i + 1, j);
						double tempCount = Double.parseDouble(str1.substring(j + 1, str1.indexOf(',', j + 1)));
						if (tempCount > 0) {
							creditsCount = creditsCount + tempCount;
						}
						if (tempString.contains("Divorce")) {
							if (hmp.containsKey(tempString)) {
								hmp.put(tempString, hmp.get(tempString) + tempCount);
							} else {
								hmp.put(tempString, tempCount);
							}
						} else {
							if (hmp.containsKey(tempString)) {
								hmp.put(tempString, hmp.get(tempString) + tempCount);
							} else {
								hmp.put(tempString, tempCount);
							}
						}
						if (hp.containsKey(tempString)) {
							hp.put(tempString, hp.get(tempString) + tempCount);
						} else {
							hp.put(tempString, tempCount);
						}
						break;
					}
				}
				count = count + 1;
			}
			Set<Map.Entry<String, Double>> set1 = hmp.entrySet();
			List<Map.Entry<String, Double>> vendList1 = new ArrayList<Map.Entry<String, Double>>(set1);
			Collections.sort(vendList1, new Comparator<Map.Entry<String, Double>>() {
				public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
					return o1.getValue().compareTo(o2.getValue());
				}
			});

			String vendString = "";
			double vendCount = 0;
			String old = "'";
			String n = "\\'";

			for (int k = 0; k < vendList1.size(); k++) {
				if (!vendList1.get(k).getKey().equals("Paycheck")) {
					vendString = vendString + "['" + vendList1.get(k).getKey().replace(old, n) + "',"
							+ vendList1.get(k).getValue() * -1 + "],";
					vendCount = vendCount + vendList1.get(k).getValue() * -1;
				}
			}
			if (hmp.containsKey("Paycheck")) {
				if (hmp.get("Divorce Lawyer Fees") != null) {
					createHTMLReport(userid, vendString, hmp.get("Paycheck"), vendCount, "Yes");
				} else {
					createHTMLReport(userid, vendString, hmp.get("Paycheck"), vendCount, "No");
				}
			} else {
				if (hmp.get("Divorce Lawyer Fees") != null) {
					createHTMLReport(userid, vendString, 0, vendCount, "Yes");
				} else {
					createHTMLReport(userid, vendString, 0, vendCount, "No");
				}
			}
			creditsCount = 0;
			divCount = 0;
			br1.close();
		}
	}
}