package WordCounter.Resutlt;

import java.util.ArrayList;
import java.util.List;

public class FileResult
{
	private List<String> errors = new ArrayList<String>();
	private String[] words;
	private String fileName;
			
	
	public FileResult(String[] words, String fileName)
	{
		this.words = words;
		this.fileName = fileName;
	}
	public List<String> getErrors()
	{
		return errors;
	}
	public void setErrors(List<String> errors)
	{
		this.errors = errors;
	}
	public String[] getWords() 
	{
		return words;
	}
	public void setWords(String[] words)
	{
		this.words = words;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
}
