package my.com.tm.portal.leasing.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public final class MapperUtil {

	private static final Logger log = LoggerFactory.getLogger(MapperUtil.class);

	private MapperUtil() {}

	public static JsonNode readJsonWithJsonNode(String file) {

		JsonNode rootNode = null;
		try {
			rootNode = new ObjectMapper().readTree(Files.readAllBytes(Paths.get(file)));
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}

		return rootNode;

	}

	public static Object getJsonObjectByStream(InputStream resourceAsStream, Class<?> clazz) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = null;
		Object obj = null;
		try {
			rootNode = objectMapper.readTree(resourceAsStream);
			obj = objectMapper.readValue(rootNode.toString(), clazz);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			rootNode = null;
		}
		return obj;
	}

	public static String getJsonString(Class<?> serializationView, Object body) {
		String json = null;
		try {
			json = new ObjectMapper().writerWithView(serializationView).writeValueAsString(body);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(), e);
		}
		return json;
	}

	public static String getJsonString(Object body) {
		String json = null;
		try {
			json = new ObjectMapper().writeValueAsString(body);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(), e);
		}
		return json;
	}


	public static Object convertJsonStringToObject(Class<?> serializationView, Object body) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = null;
		Object obj = null;
		try {
			rootNode = objectMapper.readTree(body.toString());
			obj = objectMapper.readValue(rootNode.toString(), serializationView);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			rootNode = null;
		}
		return obj;
	}

	public static Map<String, Object> getJsonMap(String file) {
		//converting json to Map
		Map<String,Object> myMap = new HashMap<String, Object>();
		try {
			byte[] mapData = Files.readAllBytes(Paths.get(file));

			//ObjectMapper objectMapper = new ObjectMapper();
			//myMap = objectMapper.readValue(mapData, HashMap.class);

			//another way
			myMap = new ObjectMapper().readValue(mapData, new TypeReference<HashMap<String,Object>>() {});

		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}

		return myMap;

	}

	public static <T> List<T> loadListFromCsv(Class<T> type, String fileName) {
		try {
			CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
			File file = new ClassPathResource(fileName).getFile();
			MappingIterator<T> readValues =  new CsvMapper().readerFor(type).with(bootstrapSchema).readValues(file);
			return readValues.readAll();
		} catch (Exception e) {
			log.error("Error occurred while loading object list from file " + fileName, e);
		}
		return Collections.emptyList();
	}
	
	
	public static <T> String converListToCsv(Class<T> type, List<?> list) {
		final CsvMapper mapper = new CsvMapper();
		final CsvSchema schema = mapper.schemaFor(type);
		try {
			return mapper.writer(schema.withUseHeader(true)).writeValueAsString(list);
		} catch (JsonProcessingException e) {
			log.error("Error occurred while coverting class to CSV ", e);
		}
		return null;
	}
}
