package com.mjc.school.repository;

public class FilePathUtils {
		private FilePathUtils() {		}

		/* Ok, you've got here constant of file locations.
			That is might to be fine for your task, but keep in mind that using absolute paths is not recommended.
			For example, other person who wants to build your project will have failed tests because he doesn't have
			files at the same path. So you can use relative paths with which java works well.
			You may check the following article for examples: https://mkyong.com/java/java-read-a-file-from-resources-folder
			Also, try to read more about relative paths vs absolute.
		 */
		public static final String NEWS_CSV = "module-repository/src/main/resources/news.csv";
		public static final String TEST_CSV = "module-service/build/resources/test/test.csv";
}