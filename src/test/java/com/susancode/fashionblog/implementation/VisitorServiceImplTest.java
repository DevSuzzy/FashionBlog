package com.susancode.fashionblog.implementation;

import com.bctech.fashionista.constants.AuthorType;
import com.bctech.fashionista.dto.request.LoginRequestDto;
import com.bctech.fashionista.dto.request.VisitorRequestDto;
import com.bctech.fashionista.dto.response.VisitorResponseDto;
import com.bctech.fashionista.entity.Likes;
import com.bctech.fashionista.entity.Visitor;
import com.bctech.fashionista.exceptions.customexceptions.VisitorNotFoundException;
import com.bctech.fashionista.exceptions.customexceptions.WrongCredentialsException;
import com.bctech.fashionista.repository.VisitorRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {VisitorServiceImpl.class})
@ExtendWith(SpringExtension.class)
class VisitorServiceImplTest {
    @MockBean
    private VisitorRepository visitorRepository;

    @Autowired
    private VisitorServiceImpl visitorServiceImpl;

    /**
     * Method under test: {@link VisitorServiceImpl#createVisitor(VisitorRequestDto)}
     */
    @Test
    void testCreateVisitor() {
        when(visitorRepository.save (any())).thenReturn(new Visitor());
        when(visitorRepository.findByEmail(any())).thenReturn(Optional.of(new Visitor()));

        assertThrows(VisitorNotFoundException.class, () -> visitorServiceImpl
                .createVisitor(new VisitorRequestDto("Dr Jane Doe", "iloveyou", "iloveyou", "jane.doe@example.org")));

        verify(visitorRepository).findByEmail( any());
    }

    /**
     * Method under test: {@link VisitorServiceImpl#createVisitor(VisitorRequestDto)}
     */
    @Test
    void testCreateVisitor2() {
        when(visitorRepository.save((Visitor) any())).thenReturn(new Visitor());
        when(visitorRepository.findByEmail((String) any())).thenReturn(Optional.empty());
        VisitorResponseDto actualCreateVisitorResult = visitorServiceImpl
                .createVisitor(new VisitorRequestDto("Dr Jane Doe", "iloveyou", "iloveyou", "jane.doe@example.org"));
        assertEquals("Dr Jane Doe", actualCreateVisitorResult.getFullName());
        assertNull(actualCreateVisitorResult.getId());
        verify(visitorRepository).save((Visitor) any());
        verify(visitorRepository).findByEmail((String) any());
    }

    /**
     * Method under test: {@link VisitorServiceImpl#createVisitor(VisitorRequestDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateVisitor3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.bctech.fashionista.dto.request.VisitorRequestDto.getEmail()" because "visitorRequestDto" is null
        //       at com.bctech.fashionista.service.implementation.VisitorServiceImpl.createVisitor(VisitorServiceImpl.java:27)
        //   See https://diff.blue/R013 to resolve this issue.

        when(visitorRepository.save((Visitor) any())).thenReturn(new Visitor());
        when(visitorRepository.findByEmail((String) any())).thenReturn(Optional.of(new Visitor()));
        visitorServiceImpl.createVisitor(null);
    }

    /**
     * Method under test: {@link VisitorServiceImpl#createVisitor(VisitorRequestDto)}
     */
    @Test
    void testCreateVisitor4() {
        when(visitorRepository.save((Visitor) any())).thenThrow(new VisitorNotFoundException("jane.doe@example.org"));
        when(visitorRepository.findByEmail((String) any()))
                .thenThrow(new VisitorNotFoundException("jane.doe@example.org"));
        assertThrows(VisitorNotFoundException.class, () -> visitorServiceImpl
                .createVisitor(new VisitorRequestDto("Dr Jane Doe", "iloveyou", "iloveyou", "jane.doe@example.org")));
        verify(visitorRepository).findByEmail((String) any());
    }

    /**
     * Method under test: {@link VisitorServiceImpl#updateVisitor(VisitorRequestDto, Long)}
     */
    @Test
    void testUpdateVisitor() {
        when(visitorRepository.save((Visitor) any())).thenReturn(new Visitor());
        when(visitorRepository.findById((Long) any())).thenReturn(Optional.of(new Visitor()));
        VisitorResponseDto actualUpdateVisitorResult = visitorServiceImpl
                .updateVisitor(new VisitorRequestDto("Dr Jane Doe", "iloveyou", "iloveyou", "jane.doe@example.org"), 123L);
        assertNull(actualUpdateVisitorResult.getFullName());
        assertNull(actualUpdateVisitorResult.getId());
        verify(visitorRepository).save((Visitor) any());
        verify(visitorRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link VisitorServiceImpl#updateVisitor(VisitorRequestDto, Long)}
     */
    @Test
    void testUpdateVisitor2() {
        when(visitorRepository.save((Visitor) any())).thenThrow(new WrongCredentialsException("jane.doe@example.org"));
        when(visitorRepository.findById((Long) any())).thenReturn(Optional.of(new Visitor()));
        assertThrows(WrongCredentialsException.class, () -> visitorServiceImpl
                .updateVisitor(new VisitorRequestDto("Dr Jane Doe", "iloveyou", "iloveyou", "jane.doe@example.org"), 123L));
        verify(visitorRepository).save((Visitor) any());
        verify(visitorRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link VisitorServiceImpl#updateVisitor(VisitorRequestDto, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateVisitor3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: source cannot be null
        //       at org.modelmapper.internal.util.Assert.notNull(Assert.java:53)
        //       at org.modelmapper.ModelMapper.map(ModelMapper.java:404)
        //       at com.bctech.fashionista.utils.ModelMapperUtils.map(ModelMapperUtils.java:26)
        //       at com.bctech.fashionista.service.implementation.VisitorServiceImpl.updateVisitor(VisitorServiceImpl.java:61)
        //   See https://diff.blue/R013 to resolve this issue.

        when(visitorRepository.save((Visitor) any())).thenReturn(null);
        when(visitorRepository.findById((Long) any())).thenReturn(Optional.of(new Visitor()));
        visitorServiceImpl
                .updateVisitor(new VisitorRequestDto("Dr Jane Doe", "iloveyou", "iloveyou", "jane.doe@example.org"), 123L);
    }

    /**
     * Method under test: {@link VisitorServiceImpl#updateVisitor(VisitorRequestDto, Long)}
     */
    @Test
    void testUpdateVisitor4() {
        Visitor visitor = mock(Visitor.class);
        when(visitor.getId()).thenReturn(123L);
        when(visitor.getFullName()).thenReturn("Dr Jane Doe");
        when(visitorRepository.save((Visitor) any())).thenReturn(visitor);
        when(visitorRepository.findById((Long) any())).thenReturn(Optional.of(new Visitor()));
        VisitorResponseDto actualUpdateVisitorResult = visitorServiceImpl
                .updateVisitor(new VisitorRequestDto("Dr Jane Doe", "iloveyou", "iloveyou", "jane.doe@example.org"), 123L);
        assertEquals("Dr Jane Doe", actualUpdateVisitorResult.getFullName());
        assertEquals(123L, actualUpdateVisitorResult.getId().longValue());
        verify(visitorRepository).save((Visitor) any());
        verify(visitorRepository).findById((Long) any());
        verify(visitor).getId();
        verify(visitor).getFullName();
    }

    /**
     * Method under test: {@link VisitorServiceImpl#updateVisitor(VisitorRequestDto, Long)}
     */
    @Test
    void testUpdateVisitor5() {
        Visitor visitor = mock(Visitor.class);
        doNothing().when(visitor).setEmail((String) any());
        doNothing().when(visitor).setFullName((String) any());
        doNothing().when(visitor).setPassword((String) any());
        Optional<Visitor> ofResult = Optional.of(visitor);
        Visitor visitor1 = mock(Visitor.class);
        when(visitor1.getId()).thenReturn(123L);
        when(visitor1.getFullName()).thenReturn("Dr Jane Doe");
        when(visitorRepository.save((Visitor) any())).thenReturn(visitor1);
        when(visitorRepository.findById((Long) any())).thenReturn(ofResult);
        VisitorResponseDto actualUpdateVisitorResult = visitorServiceImpl
                .updateVisitor(new VisitorRequestDto("Dr Jane Doe", "iloveyou", "iloveyou", "jane.doe@example.org"), 123L);
        assertEquals("Dr Jane Doe", actualUpdateVisitorResult.getFullName());
        assertEquals(123L, actualUpdateVisitorResult.getId().longValue());
        verify(visitorRepository).save((Visitor) any());
        verify(visitorRepository).findById((Long) any());
        verify(visitor1).getId();
        verify(visitor1).getFullName();
        verify(visitor).setEmail((String) any());
        verify(visitor).setFullName((String) any());
        verify(visitor).setPassword((String) any());
    }

    /**
     * Method under test: {@link VisitorServiceImpl#updateVisitor(VisitorRequestDto, Long)}
     */
    @Test
    void testUpdateVisitor6() {
        Visitor visitor = mock(Visitor.class);
        doThrow(new VisitorNotFoundException("jane.doe@example.org")).when(visitor).setEmail((String) any());
        doThrow(new VisitorNotFoundException("jane.doe@example.org")).when(visitor).setFullName((String) any());
        doThrow(new VisitorNotFoundException("jane.doe@example.org")).when(visitor).setPassword((String) any());
        Optional<Visitor> ofResult = Optional.of(visitor);
        when(visitorRepository.save((Visitor) any())).thenReturn(mock(Visitor.class));
        when(visitorRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(VisitorNotFoundException.class, () -> visitorServiceImpl
                .updateVisitor(new VisitorRequestDto("Dr Jane Doe", "iloveyou", "iloveyou", "jane.doe@example.org"), 123L));
        verify(visitorRepository).findById((Long) any());
        verify(visitor).setEmail((String) any());
    }

    /**
     * Method under test: {@link VisitorServiceImpl#updateVisitor(VisitorRequestDto, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateVisitor7() {

        Visitor visitor = mock(Visitor.class);
        doNothing().when(visitor).setEmail((String) any());
        doNothing().when(visitor).setFullName((String) any());
        doNothing().when(visitor).setPassword((String) any());
        Optional<Visitor> ofResult = Optional.of(visitor);
        when(visitorRepository.save((Visitor) any())).thenReturn(mock(Visitor.class));
        when(visitorRepository.findById((Long) any())).thenReturn(ofResult);
        visitorServiceImpl
                .updateVisitor(new VisitorRequestDto("Dr Jane Doe", "iloveyou", "iloveyou", "jane.doe@example.org"), 123L);
    }

    /**
     * Method under test: {@link VisitorServiceImpl#updateVisitor(VisitorRequestDto, Long)}
     */
    @Test
    void testUpdateVisitor8() {
        when(visitorRepository.save((Visitor) any())).thenReturn(mock(Visitor.class));
        when(visitorRepository.findById((Long) any())).thenReturn(Optional.empty());
        new VisitorNotFoundException("jane.doe@example.org");
        new VisitorNotFoundException("jane.doe@example.org");
        assertThrows(VisitorNotFoundException.class, () -> visitorServiceImpl
                .updateVisitor(new VisitorRequestDto("Dr Jane Doe", "iloveyou", "iloveyou", "jane.doe@example.org"), 123L));
        verify(visitorRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link VisitorServiceImpl#updateVisitor(VisitorRequestDto, Long)}
     */
    @Test
    void testUpdateVisitor9() {
        Visitor visitor = mock(Visitor.class);
        doNothing().when(visitor).setEmail((String) any());
        doNothing().when(visitor).setFullName((String) any());
        doNothing().when(visitor).setPassword((String) any());
        Optional<Visitor> ofResult = Optional.of(visitor);
        Visitor visitor1 = mock(Visitor.class);
        when(visitor1.getId()).thenReturn(123L);
        when(visitor1.getFullName()).thenReturn("Dr Jane Doe");
        when(visitorRepository.save((Visitor) any())).thenReturn(visitor1);
        when(visitorRepository.findById((Long) any())).thenReturn(ofResult);
        new VisitorNotFoundException("jane.doe@example.org");
        new VisitorNotFoundException("jane.doe@example.org");
        VisitorResponseDto actualUpdateVisitorResult = visitorServiceImpl
                .updateVisitor(new VisitorRequestDto("", "iloveyou", "iloveyou", "jane.doe@example.org"), 123L);
        assertEquals("Dr Jane Doe", actualUpdateVisitorResult.getFullName());
        assertEquals(123L, actualUpdateVisitorResult.getId().longValue());
        verify(visitorRepository).save((Visitor) any());
        verify(visitorRepository).findById((Long) any());
        verify(visitor1).getId();
        verify(visitor1).getFullName();
        verify(visitor).setEmail((String) any());
        verify(visitor).setPassword((String) any());
    }

    /**
     * Method under test: {@link VisitorServiceImpl#updateVisitor(VisitorRequestDto, Long)}
     */
    @Test
    void testUpdateVisitor10() {
        Visitor visitor = mock(Visitor.class);
        doNothing().when(visitor).setEmail((String) any());
        doNothing().when(visitor).setFullName((String) any());
        doNothing().when(visitor).setPassword((String) any());
        Optional<Visitor> ofResult = Optional.of(visitor);
        Visitor visitor1 = mock(Visitor.class);
        when(visitor1.getId()).thenReturn(123L);
        when(visitor1.getFullName()).thenReturn("Dr Jane Doe");
        when(visitorRepository.save((Visitor) any())).thenReturn(visitor1);
        when(visitorRepository.findById((Long) any())).thenReturn(ofResult);
        new VisitorNotFoundException("jane.doe@example.org");
        new VisitorNotFoundException("jane.doe@example.org");
        VisitorResponseDto actualUpdateVisitorResult = visitorServiceImpl
                .updateVisitor(new VisitorRequestDto("", "", "iloveyou", "jane.doe@example.org"), 123L);
        assertEquals("Dr Jane Doe", actualUpdateVisitorResult.getFullName());
        assertEquals(123L, actualUpdateVisitorResult.getId().longValue());
        verify(visitorRepository).save((Visitor) any());
        verify(visitorRepository).findById((Long) any());
        verify(visitor1).getId();
        verify(visitor1).getFullName();
        verify(visitor).setEmail((String) any());
    }

    /**
     * Method under test: {@link VisitorServiceImpl#updateVisitor(VisitorRequestDto, Long)}
     */
    @Test
    void testUpdateVisitor11() {
        Visitor visitor = mock(Visitor.class);
        doNothing().when(visitor).setEmail((String) any());
        doNothing().when(visitor).setFullName((String) any());
        doNothing().when(visitor).setPassword((String) any());
        Optional<Visitor> ofResult = Optional.of(visitor);
        Visitor visitor1 = mock(Visitor.class);
        when(visitor1.getId()).thenReturn(123L);
        when(visitor1.getFullName()).thenReturn("Dr Jane Doe");
        when(visitorRepository.save((Visitor) any())).thenReturn(visitor1);
        when(visitorRepository.findById((Long) any())).thenReturn(ofResult);
        new VisitorNotFoundException("jane.doe@example.org");
        new VisitorNotFoundException("jane.doe@example.org");
        VisitorResponseDto actualUpdateVisitorResult = visitorServiceImpl
                .updateVisitor(new VisitorRequestDto("", "", "iloveyou", ""), 123L);
        assertEquals("Dr Jane Doe", actualUpdateVisitorResult.getFullName());
        assertEquals(123L, actualUpdateVisitorResult.getId().longValue());
        verify(visitorRepository).save((Visitor) any());
        verify(visitorRepository).findById((Long) any());
        verify(visitor1).getId();
        verify(visitor1).getFullName();
    }

    /**
     * Method under test: {@link VisitorServiceImpl#deleteVisitor(Long)}
     */
    @Test
    void testDeleteVisitor() {
        doNothing().when(visitorRepository).delete((Visitor) any());
        when(visitorRepository.findById((Long) any())).thenReturn(Optional.of(new Visitor()));
        assertTrue(visitorServiceImpl.deleteVisitor(123L));
        verify(visitorRepository).findById((Long) any());
        verify(visitorRepository).delete((Visitor) any());
    }

    /**
     * Method under test: {@link VisitorServiceImpl#deleteVisitor(Long)}
     */
    @Test
    void testDeleteVisitor2() {
        doThrow(new WrongCredentialsException("jane.doe@example.org")).when(visitorRepository).delete((Visitor) any());
        when(visitorRepository.findById((Long) any())).thenReturn(Optional.of(new Visitor()));
        assertThrows(WrongCredentialsException.class, () -> visitorServiceImpl.deleteVisitor(123L));
        verify(visitorRepository).findById((Long) any());
        verify(visitorRepository).delete((Visitor) any());
    }

    /**
     * Method under test: {@link VisitorServiceImpl#deleteVisitor(Long)}
     */
    @Test
    void testDeleteVisitor3() {
        doNothing().when(visitorRepository).delete((Visitor) any());
        when(visitorRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(VisitorNotFoundException.class, () -> visitorServiceImpl.deleteVisitor(123L));
        verify(visitorRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link VisitorServiceImpl#getVisitorByEmail(String)}
     */
    @Test
    void testGetVisitorByEmail() {
        when(visitorRepository.findByEmail((String) any())).thenReturn(Optional.of(new Visitor()));
        VisitorResponseDto actualVisitorByEmail = visitorServiceImpl.getVisitorByEmail("jane.doe@example.org");
        assertNull(actualVisitorByEmail.getFullName());
        assertNull(actualVisitorByEmail.getId());
        verify(visitorRepository).findByEmail((String) any());
    }

    /**
     * Method under test: {@link VisitorServiceImpl#getVisitorByEmail(String)}
     */
    @Test
    void testGetVisitorByEmail2() {
        Visitor visitor = mock(Visitor.class);
        when(visitor.getId()).thenReturn(123L);
        when(visitor.getFullName()).thenReturn("Dr Jane Doe");
        Optional<Visitor> ofResult = Optional.of(visitor);
        when(visitorRepository.findByEmail((String) any())).thenReturn(ofResult);
        VisitorResponseDto actualVisitorByEmail = visitorServiceImpl.getVisitorByEmail("jane.doe@example.org");
        assertEquals("Dr Jane Doe", actualVisitorByEmail.getFullName());
        assertEquals(123L, actualVisitorByEmail.getId().longValue());
        verify(visitorRepository).findByEmail((String) any());
        verify(visitor).getId();
        verify(visitor).getFullName();
    }

    /**
     * Method under test: {@link VisitorServiceImpl#getVisitorByEmail(String)}
     */
    @Test
    void testGetVisitorByEmail3() {
        when(visitorRepository.findByEmail((String) any())).thenReturn(Optional.empty());
        assertThrows(VisitorNotFoundException.class, () -> visitorServiceImpl.getVisitorByEmail("jane.doe@example.org"));
        verify(visitorRepository).findByEmail((String) any());
    }

    /**
     * Method under test: {@link VisitorServiceImpl#getVisitorByEmail(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetVisitorByEmail4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.modelmapper.MappingException: ModelMapper mapping errors:
        //   1) Error mapping com.bctech.fashionista.entity.Visitor$MockitoMock$UOqx9Ryc to com.bctech.fashionista.dto.response.VisitorResponseDto
        //   1 error
        //       at org.modelmapper.internal.Errors.throwMappingExceptionIfErrorsExist(Errors.java:380)
        //       at org.modelmapper.internal.MappingEngineImpl.map(MappingEngineImpl.java:80)
        //       at org.modelmapper.ModelMapper.mapInternal(ModelMapper.java:573)
        //       at org.modelmapper.ModelMapper.map(ModelMapper.java:406)
        //       at com.bctech.fashionista.utils.ModelMapperUtils.map(ModelMapperUtils.java:26)
        //       at com.bctech.fashionista.service.implementation.VisitorServiceImpl.getVisitorByEmail(VisitorServiceImpl.java:78)
        //   org.modelmapper.MappingException: ModelMapper mapping errors:
        //   1) Failed to get value from com.bctech.fashionista.entity.Visitor.getFullName()
        //   1 error
        //       at org.modelmapper.internal.Errors.toMappingException(Errors.java:258)
        //       at org.modelmapper.internal.PropertyInfoImpl$MethodAccessor.getValue(PropertyInfoImpl.java:106)
        //       at org.modelmapper.internal.MappingEngineImpl.resolveSourceValue(MappingEngineImpl.java:197)
        //       at org.modelmapper.internal.MappingEngineImpl.propertyMap(MappingEngineImpl.java:170)
        //       at org.modelmapper.internal.MappingEngineImpl.typeMap(MappingEngineImpl.java:151)
        //       at org.modelmapper.internal.MappingEngineImpl.map(MappingEngineImpl.java:105)
        //       at org.modelmapper.internal.MappingEngineImpl.map(MappingEngineImpl.java:71)
        //       at org.modelmapper.ModelMapper.mapInternal(ModelMapper.java:573)
        //       at org.modelmapper.ModelMapper.map(ModelMapper.java:406)
        //       at com.bctech.fashionista.utils.ModelMapperUtils.map(ModelMapperUtils.java:26)
        //       at com.bctech.fashionista.service.implementation.VisitorServiceImpl.getVisitorByEmail(VisitorServiceImpl.java:78)
        //   java.lang.reflect.InvocationTargetException
        //       at org.modelmapper.internal.PropertyInfoImpl$MethodAccessor.getValue(PropertyInfoImpl.java:101)
        //       at org.modelmapper.internal.MappingEngineImpl.resolveSourceValue(MappingEngineImpl.java:197)
        //       at org.modelmapper.internal.MappingEngineImpl.propertyMap(MappingEngineImpl.java:170)
        //       at org.modelmapper.internal.MappingEngineImpl.typeMap(MappingEngineImpl.java:151)
        //       at org.modelmapper.internal.MappingEngineImpl.map(MappingEngineImpl.java:105)
        //       at org.modelmapper.internal.MappingEngineImpl.map(MappingEngineImpl.java:71)
        //       at org.modelmapper.ModelMapper.mapInternal(ModelMapper.java:573)
        //       at org.modelmapper.ModelMapper.map(ModelMapper.java:406)
        //       at com.bctech.fashionista.utils.ModelMapperUtils.map(ModelMapperUtils.java:26)
        //       at com.bctech.fashionista.service.implementation.VisitorServiceImpl.getVisitorByEmail(VisitorServiceImpl.java:78)
        //   com.bctech.fashionista.exceptions.customexceptions.VisitorNotFoundException: BLog Visitor with email: jane.doe@example.org not found
        //       at org.modelmapper.internal.PropertyInfoImpl$MethodAccessor.getValue(PropertyInfoImpl.java:101)
        //       at org.modelmapper.internal.MappingEngineImpl.resolveSourceValue(MappingEngineImpl.java:197)
        //       at org.modelmapper.internal.MappingEngineImpl.propertyMap(MappingEngineImpl.java:170)
        //       at org.modelmapper.internal.MappingEngineImpl.typeMap(MappingEngineImpl.java:151)
        //       at org.modelmapper.internal.MappingEngineImpl.map(MappingEngineImpl.java:105)
        //       at org.modelmapper.internal.MappingEngineImpl.map(MappingEngineImpl.java:71)
        //       at org.modelmapper.ModelMapper.mapInternal(ModelMapper.java:573)
        //       at org.modelmapper.ModelMapper.map(ModelMapper.java:406)
        //       at com.bctech.fashionista.utils.ModelMapperUtils.map(ModelMapperUtils.java:26)
        //       at com.bctech.fashionista.service.implementation.VisitorServiceImpl.getVisitorByEmail(VisitorServiceImpl.java:78)
        //   See https://diff.blue/R013 to resolve this issue.

        Visitor visitor = mock(Visitor.class);
        when(visitor.getId()).thenThrow(new VisitorNotFoundException("jane.doe@example.org"));
        when(visitor.getFullName()).thenThrow(new VisitorNotFoundException("jane.doe@example.org"));
        Optional<Visitor> ofResult = Optional.of(visitor);
        when(visitorRepository.findByEmail((String) any())).thenReturn(ofResult);
        visitorServiceImpl.getVisitorByEmail("jane.doe@example.org");
    }

    /**
     * Method under test: {@link VisitorServiceImpl#fetchVisitor(LoginRequestDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFetchVisitor() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.equals(Object)" because "str" is null
        //       at com.bctech.fashionista.service.implementation.VisitorServiceImpl.fetchVisitor(VisitorServiceImpl.java:85)
        //   See https://diff.blue/R013 to resolve this issue.

        when(visitorRepository.findByEmail((String) any())).thenReturn(Optional.of(new Visitor()));
        visitorServiceImpl.fetchVisitor(new LoginRequestDto("iloveyou", "jane.doe@example.org"));
    }

    /**
     * Method under test: {@link VisitorServiceImpl#fetchVisitor(LoginRequestDto)}
     */
    @Test
    void testFetchVisitor2() {
        ArrayList<Likes> likesList = new ArrayList<>();
        when(visitorRepository.findByEmail((String) any())).thenReturn(Optional.of(new Visitor("Dr Jane Doe",
                AuthorType.ADMIN, "jane.doe@example.org", "iloveyou", likesList, new ArrayList<>())));
        VisitorResponseDto actualFetchVisitorResult = visitorServiceImpl
                .fetchVisitor(new LoginRequestDto("iloveyou", "jane.doe@example.org"));
        assertEquals("Dr Jane Doe", actualFetchVisitorResult.getFullName());
        assertNull(actualFetchVisitorResult.getId());
        verify(visitorRepository).findByEmail((String) any());
    }

    /**
     * Method under test: {@link VisitorServiceImpl#fetchVisitor(LoginRequestDto)}
     */
    @Test
    void testFetchVisitor3() {
        Visitor visitor = mock(Visitor.class);
        when(visitor.getId()).thenReturn(123L);
        when(visitor.getFullName()).thenReturn("Dr Jane Doe");
        when(visitor.getPassword()).thenReturn("iloveyou");
        Optional<Visitor> ofResult = Optional.of(visitor);
        when(visitorRepository.findByEmail((String) any())).thenReturn(ofResult);
        VisitorResponseDto actualFetchVisitorResult = visitorServiceImpl
                .fetchVisitor(new LoginRequestDto("iloveyou", "jane.doe@example.org"));
        assertEquals("Dr Jane Doe", actualFetchVisitorResult.getFullName());
        assertEquals(123L, actualFetchVisitorResult.getId().longValue());
        verify(visitorRepository).findByEmail((String) any());
        verify(visitor).getId();
        verify(visitor).getFullName();
        verify(visitor).getPassword();
    }

    /**
     * Method under test: {@link VisitorServiceImpl#fetchVisitor(LoginRequestDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFetchVisitor4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.modelmapper.MappingException: ModelMapper mapping errors:
        //   1) Error mapping com.bctech.fashionista.entity.Visitor$MockitoMock$UOqx9Ryc to com.bctech.fashionista.dto.response.VisitorResponseDto
        //   1 error
        //       at org.modelmapper.internal.Errors.throwMappingExceptionIfErrorsExist(Errors.java:380)
        //       at org.modelmapper.internal.MappingEngineImpl.map(MappingEngineImpl.java:80)
        //       at org.modelmapper.ModelMapper.mapInternal(ModelMapper.java:573)
        //       at org.modelmapper.ModelMapper.map(ModelMapper.java:406)
        //       at com.bctech.fashionista.utils.ModelMapperUtils.map(ModelMapperUtils.java:26)
        //       at com.bctech.fashionista.service.implementation.VisitorServiceImpl.fetchVisitor(VisitorServiceImpl.java:86)
        //   org.modelmapper.MappingException: ModelMapper mapping errors:
        //   1) Failed to get value from com.bctech.fashionista.entity.Visitor.getFullName()
        //   1 error
        //       at org.modelmapper.internal.Errors.toMappingException(Errors.java:258)
        //       at org.modelmapper.internal.PropertyInfoImpl$MethodAccessor.getValue(PropertyInfoImpl.java:106)
        //       at org.modelmapper.internal.MappingEngineImpl.resolveSourceValue(MappingEngineImpl.java:197)
        //       at org.modelmapper.internal.MappingEngineImpl.propertyMap(MappingEngineImpl.java:170)
        //       at org.modelmapper.internal.MappingEngineImpl.typeMap(MappingEngineImpl.java:151)
        //       at org.modelmapper.internal.MappingEngineImpl.map(MappingEngineImpl.java:105)
        //       at org.modelmapper.internal.MappingEngineImpl.map(MappingEngineImpl.java:71)
        //       at org.modelmapper.ModelMapper.mapInternal(ModelMapper.java:573)
        //       at org.modelmapper.ModelMapper.map(ModelMapper.java:406)
        //       at com.bctech.fashionista.utils.ModelMapperUtils.map(ModelMapperUtils.java:26)
        //       at com.bctech.fashionista.service.implementation.VisitorServiceImpl.fetchVisitor(VisitorServiceImpl.java:86)
        //   java.lang.reflect.InvocationTargetException
        //       at org.modelmapper.internal.PropertyInfoImpl$MethodAccessor.getValue(PropertyInfoImpl.java:101)
        //       at org.modelmapper.internal.MappingEngineImpl.resolveSourceValue(MappingEngineImpl.java:197)
        //       at org.modelmapper.internal.MappingEngineImpl.propertyMap(MappingEngineImpl.java:170)
        //       at org.modelmapper.internal.MappingEngineImpl.typeMap(MappingEngineImpl.java:151)
        //       at org.modelmapper.internal.MappingEngineImpl.map(MappingEngineImpl.java:105)
        //       at org.modelmapper.internal.MappingEngineImpl.map(MappingEngineImpl.java:71)
        //       at org.modelmapper.ModelMapper.mapInternal(ModelMapper.java:573)
        //       at org.modelmapper.ModelMapper.map(ModelMapper.java:406)
        //       at com.bctech.fashionista.utils.ModelMapperUtils.map(ModelMapperUtils.java:26)
        //       at com.bctech.fashionista.service.implementation.VisitorServiceImpl.fetchVisitor(VisitorServiceImpl.java:86)
        //   com.bctech.fashionista.exceptions.customexceptions.VisitorNotFoundException: BLog Visitor with email: jane.doe@example.org not found
        //       at org.modelmapper.internal.PropertyInfoImpl$MethodAccessor.getValue(PropertyInfoImpl.java:101)
        //       at org.modelmapper.internal.MappingEngineImpl.resolveSourceValue(MappingEngineImpl.java:197)
        //       at org.modelmapper.internal.MappingEngineImpl.propertyMap(MappingEngineImpl.java:170)
        //       at org.modelmapper.internal.MappingEngineImpl.typeMap(MappingEngineImpl.java:151)
        //       at org.modelmapper.internal.MappingEngineImpl.map(MappingEngineImpl.java:105)
        //       at org.modelmapper.internal.MappingEngineImpl.map(MappingEngineImpl.java:71)
        //       at org.modelmapper.ModelMapper.mapInternal(ModelMapper.java:573)
        //       at org.modelmapper.ModelMapper.map(ModelMapper.java:406)
        //       at com.bctech.fashionista.utils.ModelMapperUtils.map(ModelMapperUtils.java:26)
        //       at com.bctech.fashionista.service.implementation.VisitorServiceImpl.fetchVisitor(VisitorServiceImpl.java:86)
        //   See https://diff.blue/R013 to resolve this issue.

        Visitor visitor = mock(Visitor.class);
        when(visitor.getId()).thenThrow(new VisitorNotFoundException("jane.doe@example.org"));
        when(visitor.getFullName()).thenThrow(new VisitorNotFoundException("jane.doe@example.org"));
        when(visitor.getPassword()).thenReturn("iloveyou");
        Optional<Visitor> ofResult = Optional.of(visitor);
        when(visitorRepository.findByEmail((String) any())).thenReturn(ofResult);
        visitorServiceImpl.fetchVisitor(new LoginRequestDto("iloveyou", "jane.doe@example.org"));
    }

    /**
     * Method under test: {@link VisitorServiceImpl#fetchVisitor(LoginRequestDto)}
     */
    @Test
    void testFetchVisitor5() {
        Visitor visitor = mock(Visitor.class);
        when(visitor.getId()).thenReturn(123L);
        when(visitor.getFullName()).thenReturn("Dr Jane Doe");
        when(visitor.getPassword()).thenReturn("foo");
        Optional<Visitor> ofResult = Optional.of(visitor);
        when(visitorRepository.findByEmail((String) any())).thenReturn(ofResult);
        assertThrows(WrongCredentialsException.class,
                () -> visitorServiceImpl.fetchVisitor(new LoginRequestDto("iloveyou", "jane.doe@example.org")));
        verify(visitorRepository).findByEmail((String) any());
        verify(visitor).getPassword();
    }

    /**
     * Method under test: {@link VisitorServiceImpl#fetchVisitor(LoginRequestDto)}
     */
    @Test
    void testFetchVisitor6() {
        when(visitorRepository.findByEmail((String) any())).thenReturn(Optional.empty());
        assertThrows(VisitorNotFoundException.class,
                () -> visitorServiceImpl.fetchVisitor(new LoginRequestDto("iloveyou", "jane.doe@example.org")));
        verify(visitorRepository).findByEmail((String) any());
    }
}

