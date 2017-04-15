package com.brinz.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.brinz.service.BillService;

@Controller
public class BillPdfController {

  @Autowired
  private BillService billingService;

  @RequestMapping(value = "/getBillAsPdf", method = RequestMethod.GET, produces={MediaType.APPLICATION_PDF_VALUE})
  public ModelAndView generateBillPdf(@RequestParam Long billId) {
    return new ModelAndView("billPdfView", "bill", billingService.getBillById(billId));
  }

}
