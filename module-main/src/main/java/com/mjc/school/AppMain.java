package com.mjc.school;

import com.mjc.school.repository.NewsGenerator;
import com.mjc.school.web.View;

public class AppMain {
		public static void main(String[] args) {
				NewsGenerator newsGenerator = new NewsGenerator();
				newsGenerator.generateNews();
				View view = new View();
				view.start();
		}
}
