package WordCounter.FileHandling;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import WordCounter.Resutlt.FileResult;

public class ResultWriter
{
	
	private File resFile;
	private final String message = "Total Words: ";
	
	public File getResultFile(String filename) throws IOException
	{
		if (resFile == null)
		{
			resFile = new  File(getNewName(filename));
			resFile.createNewFile();
			
		}
		return resFile;
	}
	
	public void writeFile(FileResult result)
	{
		try {
			getResultFile(result.getFileName());
			int count = result.getWords().length;
			FileWriter writer = new FileWriter(getResultFile(result.getFileName()));
			BufferedWriter bWriter = new BufferedWriter(writer);
			for(String word : result.getWords())
			{
				bWriter.write(word);
				bWriter.newLine();
			}
			bWriter.write(message + count);
			
			if (result.getErrors() != null && !result.getErrors().isEmpty())
			{
				bWriter.newLine();
				bWriter.write("The following errors were found");
				bWriter.newLine();
				System.out.println();
				System.out.println("The following errors were found");
				for(String error : result.getErrors())
				{
					System.out.println(error);
					bWriter.write(error);
					bWriter.newLine();
				}				
			}
			bWriter.close();
			writer.close();
			System.out.println("File has been written "+ getNewName(resFile.getName()));
			
		} catch (IOException e) {
			result.getErrors().add(e.getMessage());		 
		}
	}
	
	public String getNewName(String name)
	{
		if (name != null)
		{
			name= name.replace(".text", ".res");
		}
		else
		{
			name= "error.res";
		}
		
		return name;
	}
}
