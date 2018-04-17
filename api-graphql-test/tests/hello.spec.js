import axios from 'axios';
import {XMLHttpRequest} from 'xmlhttprequest';

global.XMLHttpRequest = XMLHttpRequest;

describe('hellos', () => {
    test('hellos', async() =>{
    const response = await
    axios.post('http://localhost:9999/graphql', {
        query: `
        {
          hellos(after: "cursor") {
            edges {
              cursor
            }
          }
        }
      `,
    });

    expect(response.status).toEqual(200);

    const {data} = response;
    expect(data).toMatchObject({
        data: {
            hellos: {
                edges: [{
                    "cursor": "aGVsbG9zLzlkYmVhNDIzLWU4NWQtNGY0NC1hMjZiLWIyNzEyOTVhZGNlOA=="
                },{
                    "cursor": "cursor=="
                },{
                    "cursor": "cursor"
                },{
                    "cursor": "cursor"
                },{
                    "cursor": "cursor"
                },{
                    "cursor": "cursor"
                },{
                    "cursor": "cursor"
                },{
                    "cursor": "cursor"
                },{
                    "cursor": "cursor"
                },{
                    "cursor": "cursor"
                },{
                    "cursor": "cursor"
                },{
                    "cursor": "cursor"
                },{
                    "cursor": "aGVsbG9zLzQzMDBkOTVlLTExZmYtNGZmOS05NWI3LWNmNTQxOWEyMDc3MA=="
                },]
            },
        },
    });
    // expect(Object.keys(data.edges)).toEqual(
    //     expect.arrayContaining(["name", "lastname", "role", "age"])
    // );
    });

});
