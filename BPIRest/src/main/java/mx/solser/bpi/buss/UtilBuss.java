package mx.solser.bpi.buss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.solser.bpi.persistence.ConvenioRepository;


@Service
public class UtilBuss {
	
private Logger LOG = LoggerFactory.getLogger(UtilBuss.class);
//
//    @Autowired
//    private UserSession session;
    

	@Autowired
	private ConvenioRepository menuRepo;



}
