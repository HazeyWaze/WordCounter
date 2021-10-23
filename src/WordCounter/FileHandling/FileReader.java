package WordCounter.FileHandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import WordCounter.Resutlt.FileResult;

public class FileReader 
{
	private final String PATTERN = "[A-Za-z]+'[A-Za-z]+|[A-Za-z]+|[0-9]+";
	private String fileName;
	private String fileNameShort;
	private List<String> words = new ArrayList<String>();
	private List<String> errors = new ArrayList<String>();

	public FileReader(String fileName) 
	{
		this.fileName = fileName;
	}
	
	public String getFileName()
	{
		return fileName;		
	}
	

	public List<String> getWords() 
	{
		return words;
	}

	public void setWords(List<String> words) 
	{
		this.words = words;
	}

	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}
	
	
	public boolean  isValidURL()
	{
		try
		{
			URI url = new URL(getFileName()).toURI();
			return true;
		} catch (URISyntaxException | MalformedURLException e) 
		{
			return false;
		}		
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	public FileResult runFile()
	{
		Scanner scanner = null;
		if (isValidURL())
		{
			URL url = null;
			try 
			{
				url = new URL(getFileName());
			} catch (MalformedURLException e) 
			{
				errors.add(e.getMessage());
				
			}
			try 
			{
				if (url != null)
				{
					scanner = new Scanner(url.openStream());
					fileNameShort =  url.getFile().substring(1);
				}				
			} catch (IOException e)
			{
				errors.add(e.getMessage());
				
			}
		}
		else
		{
			File file = new File(getFileName());
			fileNameShort = file.getName();
			try {
				scanner = new Scanner(file);
			} catch (FileNotFoundException e)
			{
				errors.add(e.getMessage());
				
			}
		}
		if (scanner != null)
		{
			while(scanner.hasNextLine())
			{
				String line = scanner.nextLine();	
				Pattern pattern = Pattern.compile(PATTERN, Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(line);
				while (matcher.find())
				{
					String word = matcher.group();
				    words.add(word);
				}
			}						
			scanner.close();
		}
		else
		{
			errors.add("Could not readfile");
		}
		String[] resArr = new String[words.size()];
		words.toArray(resArr);
		FileResult result  = new FileResult(resArr, fileNameShort);
		result.setErrors(errors);
		return result ;
	}
	
}
