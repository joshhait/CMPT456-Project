package org.apache.lucene.demo;

import java.util.Scanner;
import java.nio.file.Paths;
import java.io.IOException;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.index.DirectoryReader;

public class SimpleMetrics {
    public static void main(String[] args) {
        
         // 1. Get Term/query word from user:
         Scanner input = new Scanner(System.in);
         System.out.println("Enter Query: ");
         String inputTerm = input.nextLine();

        // 1a. Make the user input into a term object
        Term term = new Term("contents", inputTerm);

         // 2. Create the Index Reader
        IndexReader reader = null;
        try {
            Directory dir = FSDirectory.open(Paths.get("/lucene-solr/index"));
            reader = DirectoryReader.open(dir);

            // 3. Use IndexReader.docFreq(Term term) to get the document frequency (number of docs containing the term)
            int docFreq = reader.docFreq(term);

            // 4. Use IndexReader.totalTermFreq(Term term) to get the total number of occurrences of termw accross all documents
            long termFreq = reader.totalTermFreq(term);

            // 5. Display results to the user.
            System.out.println("Query term: " + inputTerm + "\n\tDocument Frequency: " + docFreq + "\n\tTerm Frequency: " + termFreq);

        } catch (IOException e) {
            System.out.println("IOException raised");
        }

    }
}