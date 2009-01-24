(ns clj-record.test.validation-built-ins-test
  (:use clojure.contrib.test-is)
  (:require [clj-record.validation.built-ins :as vfn]))


(deftest match-creates-a-matching-fn-for-the-given-pattern
  (let [matches-foo? (vfn/match #"foo")]
    (is (matches-foo? "yes foo does"))
    (are (not (matches-foo? _1))
      "no bar doesn't"
      nil
      123))
  (testing "non-strings are str'd so they may match"
    (is ((vfn/match #"123") 41234))))

(deftest non-match-creates-an-anti-matching-fn-for-the-given-pattern
  (let [non-matches-foo? (vfn/non-match #"foo")]
    (is (not (non-matches-foo? "foo doesn't not")))
    (are (non-matches-foo? _1)
      "bar non-matches"
      nil
      123)))

(deftest numeric?
  (are (vfn/numeric? _1)
    123
    "123")
  (are (not (vfn/numeric? _1))
    "foo"))