
public class DijkstraSP
{
	private double[] distTo;          // shortest path distance
	private WeightedDiedge[] edgeTo;    // shortest path, last edge
	private MinHeap<Integer> minPQ;    // vertices minHeap

    public DijkstraSP( WeightedDigraph graph, int source )
	    {
	      
		    for (WeightedDiedge e : graph.edges())
		    {
			    if (e.weight() < 0)
			    {
				    throw new IllegalArgumentException();
			    }
		    }

		    distTo = new double[graph.V()];

		    for (int v = 0; v < graph.V(); v++)
		    {
			    distTo[v] = Double.MAX_VALUE;
		    }

		    distTo[source] = 0.0;
		    edgeTo = new WeightedDiedge[graph.V()];

		    minPQ = new MinHeap<Integer>(graph.V());
			distTo[source]=0.0;
			minPQ.insert(source); 

		while (!minPQ.isEmpty())
		{
			int minV = minPQ.delMin();
			for (WeightedDiedge e : graph.neighbors(minV))
			{  
				int w = e.to();
				int x = e.from();
				double baseLine = distTo[x] + e.weight();
				if (distTo[w] > baseLine)
				{
					distTo[w] = baseLine;
					edgeTo[w] = e;
					minPQ.insert(w);

				}
			}

		}


		}
    /**
     * Returns whether or not a path exists from the source to v.
     * @param v
     * @return true if a path exists from the source to v, false otherwise
     */
    public boolean hasPathTo( int v )
    {
        System.out.printf("haspathto " + distTo[v]);
        return distTo[v] < Double.MAX_VALUE;
    }

	//public void Path see figure 14.26
    
    /**
     * Returns distance to the specified vertex v.  If the value is
     * Double.MAX_VALUE, the vertex is not reachable from the source.
     * @param v
     * @return distance to v from s
     */
    public double distTo( int v )
    {
    return distTo[v];
    }
    
    /**
     * Returns path from the source to the given vertex v, in that order.
     * @param v
     * @return path from the source to v, starts with source, ends with v
     *         returns a null if no path exists
     */
    public Iterable<WeightedDiedge> pathTo( int v )
    {

    if (!hasPathTo(v))
	    {
		    System.out.println("this means hasPathto v is false.");

		    return null;
	    }
	    Stack<WeightedDiedge> path = new Stack<WeightedDiedge>();
		//for edge; edge not null; previous edge
        //WeightedDiedge e = edgeTo[v];
       System.out.println("inside pathto(int v). edgeTo[v]=  " + edgeTo[v] + "\n");
        System.out.println("right before pathto while loop");

    //while (e != null)
	   for (WeightedDiedge e = edgeTo[v]; e != null; e = edgeTo[e.from()])//!e.equals(null)
	    {
		    System.out.println("Inside pathTo for loop ");
		    //take e and edges of neighbors? e.compareTo(neighbors(edgeTo[v])
		   // if (e.compareTo(edgeTo[e.from()]) <= 0)
		    //{
			    path.push(e);
		         
			    System.out.println("inside pathto for loop\n");
		   // }
	    }
        path.push(edgeTo[v]);
        //Return stack
	    return path;

    }
    
    
    //---------- DO NOT MODIFY BELOW THIS LINE ----------//

    /**
     * Unit test main for the DijkstraSP class.
     * @param args 
     * @throws java.io.FileNotFoundException 
     */
    public static void main( String[] args ) throws java.io.FileNotFoundException
    {
        if( args.length != 2 )
        {
            String u = "Usage: DijkstraSP <filename> <source>";
            Stdio.println(u);
            return;
        }
        
        String fileName = args[0];
        int source      = Integer.parseInt(args[1]);
        WeightedDigraph graph = GraphFactory.makeWeightedDigraph(fileName);
        Stdio.println( "Graph: "+graph );
        
        DijkstraSP dijkstraSP = new DijkstraSP( graph, source );
        Stdio.println( "Paths to source: "+source );
        for (int vertexId = 0; vertexId < graph.V(); vertexId++)
        {
            Stdio.print( "  path for "+vertexId+" : " );
            if( dijkstraSP.hasPathTo(vertexId) )
            {
                for( WeightedDiedge path : dijkstraSP.pathTo(vertexId) )
                {
                    if( path != null ) Stdio.print( path.toString() );
                }
            }
            Stdio.println( "" );
        }
    }
}
