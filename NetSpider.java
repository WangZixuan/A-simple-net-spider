/**This code is to spider http://www.ifeng.com's top news.
 * @author Zixuan
 * Date 2015-3-28
 */
import java.io.*;
import java.net.*;
import java.util.regex.*;
public class NetSpider
{

	public static void main(String[] args) 
	{	
		StringBuffer pageBuffer = new StringBuffer();	
		String line;
		try 
		{
		 	URL url = new URL("http://www.ifeng.com");
			// Open connection to URL for reading.
			 BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
			// Read page into buffer.		    
			 pageBuffer = new StringBuffer();
			while ((line = reader.readLine()) != null)
				pageBuffer.append(line);
		    	//System.out.println(pageBuffer.toString());
		} 
		catch (Exception e) 
		{
		    	e.printStackTrace();
		}

		//Split first time.
		String pattern = "</a></div></div>";	
		String[] arr = Pattern.compile(pattern).split(pageBuffer.toString());
		line = arr[1];
		
		//Split again.
		pattern = "</ul></div>";
		arr = Pattern.compile(pattern).split(line);
		line = arr[0];
		
		pattern = "href=\"";
		String[] info = Pattern.compile(pattern).split(line);
		pattern = "\" target=\"_blank\">";
		
		//for (int i = 1; i < info.length; i++)
		//	System.out.println(info[i]);
		
		for (int i = 1; i < info.length; i++)
		{
			String temp[] = Pattern.compile(pattern).split(info[i]);
			info[i] = "";
			for (int j = 0; j < temp.length; j++)
			{
				if (1 == j)
					temp[1] = Pattern.compile("</a>").split(temp[1])[0];
				info[i] += temp[j];
				info[i] += "\t\t";
			}
		}
		
		for (int i = 1; i < info.length; i++)
			System.out.println(info[i]);
			
	}

}
