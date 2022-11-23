package com.chaeking.api.faq.application.service;

import com.chaeking.api.faq.application.port.in.GetFaqQuery;
import com.chaeking.api.faq.application.port.out.LoadFaqPort;
import com.chaeking.api.faq.domain.Faq;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
class GetFaqService implements GetFaqQuery {
    private final LoadFaqPort loadFaqPort;

    @Override
    public List<Faq> getFaqs(Pageable pageable) {
        return loadFaqPort.loadFaqs(pageable);
    }

}
