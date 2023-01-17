package com.chaeking.api.faq.adapter.in.web;

import com.chaeking.api.faq.application.port.in.GetFaqQuery;
import com.chaeking.api.faq.application.port.out.FaqDetail;
import com.chaeking.api.faq.application.port.out.FaqSimple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(controllers = GetFaqController.class)
class GetFaqControllerTest {
    MockMvc mockMvc;

    @MockBean
    GetFaqQuery getFaqQuery;

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new GetFaqController(getFaqQuery))
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    void faqs() throws Exception {
        given(getFaqQuery.getFaqSimples(PageRequest.of(0, 3, Sort.by(Sort.Order.desc("id")))))
                .willReturn(List.of(new FaqSimple(3L, "FAQ 3", "2023-01-15 09:30:00"),
                        new FaqSimple(2L, "FAQ 2", "2023-01-10 18:00:00"),
                        new FaqSimple(1L, "FAQ 1", "2023-01-09 09:30:00")));

        ResultActions resultActions = mockMvc.perform(
                        MockMvcRequestBuilders.get("/v1/faqs")
                                .header("Content-Type", "application/json")
                                .param("page", "0")
                                .param("size", "3")
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        resultActions
                .andDo(print())
                .andDo(document("faqs-get",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                parameterWithName("size").description("조회할 FAQ 수").optional(),
                                parameterWithName("page").description("페이지").optional()
                        )
                ));
    }

    @Test
    void faq() throws Exception {

        given(getFaqQuery.getFaqDetail(1L))
                .willReturn(new FaqDetail(1L, "FAQ 1", "첫번째 FAQ입니다!!", "2023-01-09"));

        ResultActions resultActions = mockMvc.perform(
                        RestDocumentationRequestBuilders.get("/v1/faqs/{id}", 1)
                                .header("Content-Type", "application/json")
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        resultActions
                .andDo(print())
                .andDo(document("faq-get",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("id").description("FAQ id")
                        )
                ));
    }
}