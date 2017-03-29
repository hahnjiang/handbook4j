package org.hahn.sample.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.index.memory.MemoryIndex;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.QueryBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by jianghan on 2017/2/22.
 */
public class LuceneSample {

    public static void main(String[] args) throws Exception {
        example1();
        example2();
    }

    public static List<Document> createDocuments(int num) {
        List<Document> docs = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Document doc = new Document();
            doc.add(new TextField("id", String.valueOf(1000 + i), Field.Store.YES));
            doc.add(new TextField("name", String.valueOf(1000 + i), Field.Store.NO));
            doc.add(new TextField("tag_list", createTagList(), Field.Store.YES));
            docs.add(doc);
        }
        return docs;
    }

    public static String createTagList() {
        Random r = new Random(System.nanoTime());
        return String.format("tag%02d tag%02d tag%02d", r.nextInt(10), r.nextInt(10), r.nextInt(10));
    }

    public static void example2() throws Exception {
        // Index
        List<Document> docs = createDocuments(10);
        StandardAnalyzer analyzer = new StandardAnalyzer();
        Directory index = new RAMDirectory();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter w = new IndexWriter(index, config);
        docs.forEach(doc -> {
            try {
                System.out.println(doc);
                w.addDocument(doc);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        w.close();

        // Query
        QueryBuilder qb = new QueryBuilder(analyzer);
        Query q = qb.createPhraseQuery("tag_list", "tag05");

        // Search
        int hitsPerPage = 10;
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage);
        searcher.search(q, collector);
        ScoreDoc[] hits = collector.topDocs().scoreDocs;

        // Display
        System.out.println(hits.length);
        for (int i = 0; i < hits.length; i++) {
            System.out.println(searcher.doc(hits[i].doc).get("id"));   // Store.YES
            System.out.println(searcher.doc(hits[i].doc).get("name")); // Store.NO
        }
    }

    public static void example1() throws Exception {
        Analyzer analyzer = new StandardAnalyzer();
        MemoryIndex index = new MemoryIndex();
        index.addField("item_list", "1001 1002 1003", analyzer);
        IndexSearcher searcher = index.createSearcher();
        Collector c = new TotalHitCountCollector();
        searcher.search(new TermQuery(new Term("item_list", "1004")), c);
        System.out.println(c);
    }

}
