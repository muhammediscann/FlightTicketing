import com.flight.ticketing.exception.OperationResultException;
import com.flight.ticketing.model.entity.AirlineCompanyFlightEntity;
import com.flight.ticketing.model.entity.BookFlightEntity;
import com.flight.ticketing.model.enumeration.BookFlightCode;
import com.flight.ticketing.model.request.BookFlightRequest;
import com.flight.ticketing.model.response.BookFlightKey;
import com.flight.ticketing.model.response.OperationResult;
import com.flight.ticketing.repository.AirlineCompanyFlightRepository;
import com.flight.ticketing.repository.BookFlightRepository;
import com.flight.ticketing.service.BookFlightServiceImpl;
import com.flight.ticketing.service.base.IAirlineCompanyFlightService;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BookFlightTest {

    @Mock
    private IAirlineCompanyFlightService airlineCompanyFlightService;

    @Mock
    private AirlineCompanyFlightRepository airlineCompanyFlightRepository;

    @InjectMocks
    private BookFlightServiceImpl bookFlightService;

    @Mock
    private BookFlightRepository bookFlightRepository;
    

    final long airlineCompanyFlightId = 1L;
    final int remainingTicketCount = 10;
    final String pnrCode = "AAAABBBB";

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createBookFlightTest_OK() {
        BookFlightRequest request = BookFlightRequest.builder().airlineCompanyFlightId(airlineCompanyFlightId).build();

        AirlineCompanyFlightEntity airlineCompanyFlightEntity = AirlineCompanyFlightEntity.builder()
                .airlineCompanyFlightId(airlineCompanyFlightId)
                .remainingTicketCount(remainingTicketCount)
                .build();

        BookFlightEntity bookFlightEntity = BookFlightEntity.builder()
                .bookFlightId(2L)
                .airlineCompanyFlight(airlineCompanyFlightEntity)
                .bookFlightCode(BookFlightCode.BOOKED)
                .pnrCode(pnrCode)
                .build();

        OperationResult operationResult = OperationResult.createSuccessResult();

        doReturn(Optional.of(airlineCompanyFlightEntity)).when(airlineCompanyFlightRepository).findById(anyLong());
        doReturn(airlineCompanyFlightEntity).when(airlineCompanyFlightService).inquireAndValidateAirlineCompanyFlight(anyLong());
        doReturn(bookFlightEntity).when(bookFlightRepository).save(any(BookFlightEntity.class));
        doReturn(operationResult).when(airlineCompanyFlightService).updateAirlineCompanyFlight(any(AirlineCompanyFlightEntity.class), any(BookFlightCode.class));

        BookFlightKey bookFlightKey = bookFlightService.createBookFlight(request);

        Assertions.assertNotNull(bookFlightKey);
        Assertions.assertEquals(bookFlightKey.getPnrCode(), pnrCode);
    }
}
