(ns bookstore.core-test
  (:require [clojure.test :refer :all]
         ;   [bookstore.core :refer :all] 
            [bookstore.dao :refer :all]
            ))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 0))))


(deftest fetch-records 
  (testing "fetch query table in derby db"
    (is (>= (count (fetch-data "select * from authors")) 0))
    )
  )


(deftest fetch-records-2
  (testing "fetch query table in derby db"
    (is (>= (count (fetch-data "select * from authors where authorid = ?" 2)) 0))))


(deftest get-query-md
      (testing "get metadata for the below query"
        (is (vector? (get-result-md "select * from titles") )))) 

(deftest get-table-md
      (testing "get metadata for the table"
        (is (seq? (get-table-metadata "titles"))))) 


(deftest all-table-md
      (testing "fetch all tables in the database"
        (is (seq? (all-tables-metadata))))) 


(deftest execute-sql
      (testing "Context of the test assertions"
        (is (map? (execute-query "select * from titles"))))) 

(deftest fetch-contacts
      (testing "Fetch contacts"
        (is (vector? (fetch-all-people))))
  ) 


((deftest contact-by-lastname
      (testing "fetch contact by lastname"
        (is (vector? (fetch-by-lastname "Alozie"))))) )


(deftest new-contact
      (testing "Context of the test assertions"
        (is (map? (new-person "mejri" "hannibal" "hmejri@gmail.com" "09014713838"))))) 


(deftest insert-contact
      (testing "insert contact"
        (is (seq? (insert-person {
                                  :firstname "Diego",
                                  :lastname "Dallot", 
                                  :email "ddalot@gmail.com",
                                  :phone "08023333129"
                                  }
                                 )
                  )
            )
        )
  ) 