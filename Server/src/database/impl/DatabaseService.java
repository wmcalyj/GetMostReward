package database.impl;

import values.constant.Values;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class DatabaseService {

	private static MongoDatabase mongoDB;
	private static MongoClient mongoClient;

	private DatabaseService() {
		// Disable creating a new database service
	}

	public static MongoDatabase getMongoDBInstance(String dbName) {
		// Only allow one instance of client & db
		if (mongoDB == null) {
			if (mongoClient == null) {
				mongoClient = new MongoClient();
			}
			mongoDB = mongoClient.getDatabase(dbName);
		}
		return mongoDB;
	}

	public static MongoDatabase getDefaultMongoDBInstance() {
		// Only allow one instance of client & db
		if (mongoDB == null) {
			if (mongoClient == null) {
				mongoClient = new MongoClient();
			}
			mongoDB = mongoClient.getDatabase(Values.Database.DEFAULT_DB_NAME);
		}
		return mongoDB;
	}

}
