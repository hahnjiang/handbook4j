package org.hahn.learning.fb;

import com.google.flatbuffers.FlatBufferBuilder;
import com.xiaomi.cube.book.Book.Book;
import com.xiaomi.cube.book.Book.IScore;
import com.xiaomi.cube.book.Book.SScore;

import java.nio.ByteBuffer;
import java.util.Collections;

/**
 * Created by jianghan on 2017/2/28.
 */
public class FlatBuffersSample {

    public static void main(String[] args) {
        testBook();
    }

    public static void testBook() {
        FlatBufferBuilder builder = new FlatBufferBuilder(1024);

        int id = builder.createString("1001");

        int[] spos = new int[3];
        spos[0] = SScore.createSScore(builder, builder.createString("10"), 1.0);
        spos[1] = SScore.createSScore(builder, builder.createString("11"), 1.0);
        spos[2] = SScore.createSScore(builder, builder.createString("12"), 1.0);
        int us = Book.createUserScoreVector(builder, spos);

        Book.startBook(builder);
        Book.addId(builder, id);
        Book.addSimilarBook(builder, IScore.createIScore(builder, 2L, 1.0));
        Book.addSimilarBook(builder, IScore.createIScore(builder, 1L, 1.0));
        Book.addSimilarBook(builder, IScore.createIScore(builder, 3L, 1.0));
        Book.addUserScore(builder, us);
        int root = Book.endBook(builder);
        builder.finish(root);

        byte[] bytes = builder.sizedByteArray();
        ByteBuffer bb = ByteBuffer.allocateDirect(bytes.length);
        bb.put(bytes);
        bb.rewind();

        // Get an accessor to the root object inside the buffer.
        Book book = Book.getRootAsBook(bb);
        System.out.println(book.toString());
        System.out.println(book.similarBook(0).id());
        System.out.println(book.similarBook(1).id());
        System.out.println(book.similarBook(2).id());

        System.out.println(book.userScore(0).id());
        System.out.println(book.userScore(1).id());
        System.out.println(book.userScore(2).id());
        System.out.println(book.userScoreLength());
        System.out.println(find(book, "09"));
        System.out.println(find(book, "10").id());
        System.out.println(find(book, "11").id());
        System.out.println(find(book, "12").id());
        System.out.println(find(book, "13"));
    }

    public static SScore find(Book book, String key) {
        int l = 0, r = book.userScoreLength() - 1;
        while (l <= r) {
            int m = (l + r) >> 1;
            SScore us = book.userScore(m);
            if (us.id().equals(key)) {
                return us;
            } else if (us.id().compareTo(key) > 0) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return null;
    }

}
