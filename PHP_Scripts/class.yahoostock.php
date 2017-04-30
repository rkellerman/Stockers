<?php

class YahooStock {

	private $stocks = array();
	private $format;

	public function addStock($stock){
		$this->stocks[] = $stock;
	}

	public function addFormat($format){
		$this->format = $format;
	}

	public function getQuotes(){
		$result = array();
		$format = $this->format;

		foreach ($this->stocks as $stock){
                
                         $url = "http://finance.yahoo.com/d/quotes.csv?s=$stock&f=$format&e=.csv";
                         //$url = urlencode('http://finance.yahoo.com/d/quotes.csv?s=$stock&f=$format&e=.csv');
                         
                         /*
                         $curl_handle=curl_init();
                         curl_setopt($curl_handle, CURLOPT_URL,$url);
                         curl_setopt($curl_handle, CURLOPT_CONNECTTIMEOUT, 2);
                         curl_setopt($curl_handle, CURLOPT_RETURNTRANSFER, 1);
                         curl_setopt($curl_handle, CURLOPT_USERAGENT, 'PHP');
                         $s = curl_exec($curl_handle);
                         curl_close($curl_handle);
                         */
                         
                         
                         
                         
			 $s = file_get_contents($url);
                         
			 $data = explode(',', $s);
			 $result[$stock] = $data;
		}

		return $result;
	}
}