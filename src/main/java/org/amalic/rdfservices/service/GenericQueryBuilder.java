package org.amalic.rdfservices.service;

public class GenericQueryBuilder extends AbstractQueryBuilder {

	public static String graphs() {
		return "select (?g as ?Graph) (count(?p) as ?Statements) where" + 
				"{graph ?g { [] ?p [] }} group by ?g order by ?g";
	}
	
	public static String getClasses(String graph) {
		return "select (?c as ?Class) (count(?c) as ?Instances) where {" + 
				checkGraph("[] a ?c ", graph) + 
				"} group by ?c order by ?c";
	}
	
	public static String listInstances(String graph, String className, Long page, Long limit) {
		return paginate(
				"select (?s as ?Instance) where {" + 
				checkGraph(String.format("?s a <%s>.", className), graph) + 
				"}"
			, page, limit);
	}
	
	public static String getPropertiesOfInstance(String graph, String className, String id) {
		return "select (?p as ?Property) (?o as ?Value) where {" + 
				checkGraph(String.format(" <%s> a <%s>; ?p ?o.", id, className), graph) + 
				"} order by ?p";
	}
	
	public static String executeSparql(String sparql, Long page, Long limit) {
		return paginate(sparql, page, limit);
	}
	
}
