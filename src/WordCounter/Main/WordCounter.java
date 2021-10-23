package WordCounter.Main;


import WordCounter.FileHandling.ResultWriter;
import WordCounter.FileHandling.FileReader;

import WordCounter.Resutlt.FileResult;

public class WordCounter {

	public static void main(String[] args) {
		if (args.length > 0)
		{
			System.out.println("File name received: "+args[0]);
			FileReader reader = new FileReader(args[0].trim()); 
			FileResult res = reader.runFile();
			ResultWriter rw= new ResultWriter();
			rw.writeFile(res);
		}
		else
		{
			ResultWriter rw= new ResultWriter();
			FileResult res = new FileResult(new String[] {},"error.res");
			res.getErrors().add("No file name was not given");
			rw.writeFile(res);
		}
	}
}
