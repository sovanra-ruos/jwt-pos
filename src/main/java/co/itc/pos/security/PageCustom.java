package co.itc.pos.security;

import co.itc.pos.utils.CustomPage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


public class PageCustom {

    public static <T> CustomPage<T> customPage(Page<T> page) {

        CustomPage<T> customPage = new CustomPage<>();

        // Check if page has previous
        customPage.setPrevious(page != null && page.hasPrevious());

        // Check if page has next
        customPage.setNext(page != null && page.hasNext());

        // Set total elements
        customPage.setTotal((int) page.getTotalElements());
        customPage.setTotalElements(page.getTotalElements());

        // Set results
        customPage.setResults(page.getContent());

        return customPage;
    }

}