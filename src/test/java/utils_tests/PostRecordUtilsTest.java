package utils_tests;

import magisterka.spring.repo.PostRecordRepository;
import magisterka.spring.repo.jpa.PostRecord;
import magisterka.spring.utils.PostRecordUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostRecordUtilsTest {

    @Mock
    private PostRecordRepository repository;

    @InjectMocks
    private PostRecordUtils utils;

    @Test
    void getRecordsShouldUsePagingAndSortById() {
        PostRecord r1 = new PostRecord();
        r1.id = 1;
        PostRecord r2 = new PostRecord();
        r2.id = 2;

        when(repository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(r1, r2)));

        List<PostRecord> result = utils.getRecords(2);

        ArgumentCaptor<Pageable> captor = ArgumentCaptor.forClass(Pageable.class);
        verify(repository).findAll(captor.capture());
        Pageable pageable = captor.getValue();

        assertThat(result).hasSize(2);
        assertThat(pageable.getPageNumber()).isEqualTo(0);
        assertThat(pageable.getPageSize()).isEqualTo(2);
        assertThat(pageable.getSort().getOrderFor("id")).isNotNull();
        assertThat(pageable.getSort().getOrderFor("id").isAscending()).isTrue();
    }

    @Test
    void deleteRecordsShouldDeleteOnlyLimitedPageAndReturnDeletedCount() {
        PostRecord r1 = new PostRecord();
        r1.id = 10;
        PostRecord r2 = new PostRecord();
        r2.id = 11;

        when(repository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(r1, r2)));

        int deleted = utils.deleteRecords(2);

        verify(repository).deleteAll(List.of(r1, r2));
        assertThat(deleted).isEqualTo(2);
    }
}