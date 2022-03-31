package rs.ac.ni.pmf.oop3.vezba03;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Downloader
{
	public static void main(String[] args) throws IOException
	{
		final FileDownloader fileDownloader = new FileDownloader();

//		final List<String> lines = fileDownloader
//			.readLines("F:\\JavaProjects\\2022\\Vezbe01\\src\\main\\resources\\log4j.properties");
//			.readLines("c:\\letters.txt");
//			.readLines("/brojevi.txt");

/*		final List<String> lines = fileDownloader.readLines("links.txt");
		final List<URL> urls = fileDownloader.getLinks(lines);

		for (final URL url : urls)
		{
			System.out.println(url);
		}*/

		fileDownloader.readLines("links.txt").stream()
			.map(fileDownloader::getLink)
			.filter(Optional::isPresent)
			.map(Optional::get)
			.forEach(fileDownloader::downloadFile);
	}

	private static void printFileSize(FileDownloader fileDownloader, URL url)
	{
		try
		{
			System.out.println(fileDownloader.getFileSize(url));
		}
		catch (IOException e)
		{
			log.error("File '" + url.getFile() + "' cannot be downloaded");
		}
	}
}
