package utils_tests;

import com.mongodb.client.result.DeleteResult;
import magisterka.spring.repo.MongoRecordRepository;
import magisterka.spring.repo.mongo.MongoRecord;
import magisterka.spring.utils.MongoRecordUtils;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class MongoRecordUtilsTest {


        @Mock
        private MongoRecordRepository repository;

        @Mock
        private MongoTemplate mongoTemplate;

        @InjectMocks
        private MongoRecordUtils utils;

        @Test
        void getRecordsShouldApplyLimitOnQuery() {
            when(mongoTemplate.find(any(Query.class), eq(MongoRecord.class))).thenReturn(List.of());

            utils.getRecords(5);

            ArgumentCaptor<Query> captor = ArgumentCaptor.forClass(Query.class);
            verify(mongoTemplate).find(captor.capture(), eq(MongoRecord.class));
            assertThat(captor.getValue().getLimit()).isEqualTo(5);
        }

        @Test
        void deleteRecordsShouldReturnZeroWhenNothingFound() {
            when(mongoTemplate.find(any(Query.class), eq(MongoRecord.class))).thenReturn(List.of());

            int deleted = utils.deleteRecords(3);

            assertThat(deleted).isZero();
            verify(mongoTemplate, never()).remove(any(Query.class), eq(MongoRecord.class));
        }

        @Test
        void deleteRecordsShouldRemoveMatchedIdsAndReturnDeletedCount() {
            MongoRecord r1 = new MongoRecord();
            r1.id = "a";
            MongoRecord r2 = new MongoRecord();
            r2.id = "b";

            when(mongoTemplate.find(any(Query.class), eq(MongoRecord.class))).thenReturn(List.of(r1, r2));
            when(mongoTemplate.remove(any(Query.class), eq(MongoRecord.class)))
                    .thenReturn(DeleteResult.acknowledged(2));

            int deleted = utils.deleteRecords(2);

            assertThat(deleted).isEqualTo(2);
            verify(mongoTemplate).remove(any(Query.class), eq(MongoRecord.class));
        }
    }

