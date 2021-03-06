package org.neo4j.tutorial;

import org.neo4j.cypher.ExecutionEngine;
import org.neo4j.cypher.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.kernel.impl.util.StringLogger;

public class AwesomenessRatingEngine
{
    public double rateAwesomeness( GraphDatabaseService db, long nodeId )
    {
        ExecutionEngine engine = new ExecutionEngine( db, StringLogger.DEV_NULL );
        String cql = "start n=node(" + nodeId + "), doctor=node:characters(character='Doctor') "
                + "match p=shortestPath(n-[*..15]-doctor)"
                + "return length(p) as hops";


        ExecutionResult result = engine.execute( cql );

        int hops = Integer.valueOf( String.valueOf( result.javaColumnAs( "hops" ).next() ) );

        return 100 / ((hops + 1) * 1.0);
    }
}
