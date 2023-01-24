package com.mjc.school.repository;

import lombok.NoArgsConstructor;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.ThreadLocalRandom;

import static com.mjc.school.repository.FilePathUtils.NEWS_CSV;

@NoArgsConstructor
public class NewsGenerator {
		private static final ZoneOffset ZONE_OFFSET = ZoneOffset.ofHours(0);

		public void generateNews() {
				List<String> list = new ArrayList<>();
				try (BufferedReader fileReader = new BufferedReader(new FileReader("module-repository/src/main/resources/content.txt"))){
						list = fileReader.lines().toList();
				} catch (IOException e) {
						e.printStackTrace();
				}
				String[] titles = list.get(0).split(",");
				String[] content = list.subList(1, list.size() - 1).toArray(new String[0]);
				try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(NEWS_CSV))) {
						for (long i = 0L; i < 20; i++) {
								StringJoiner sj = new StringJoiner(";");
								sj.add(String.valueOf(i))
												.add(titles[ThreadLocalRandom.current().nextInt(0, titles.length)])
												.add(content[ThreadLocalRandom.current().nextInt(0, content.length)])
												.add(randomDate().toString()).add(randomDate().toString())
												.add(String.valueOf(ThreadLocalRandom.current().nextLong(1, 7)))
												.add("\n");
								bufferedWriter.write(sj.toString());
						}
				} catch (IOException e) {
						e.printStackTrace();
				}
		}
		private LocalDateTime randomDate() {
				long startMillis = LocalDateTime.now().minus(Duration.ofDays(10)).toEpochSecond(ZONE_OFFSET);
				long endMillis = LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(0));
				long randomMillisSinceEpoch = ThreadLocalRandom.current().nextLong(startMillis, endMillis);
				return LocalDateTime.ofEpochSecond(randomMillisSinceEpoch, 0, ZoneOffset.ofHours(0));
		}
}