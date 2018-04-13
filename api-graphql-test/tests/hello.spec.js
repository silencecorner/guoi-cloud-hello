import axios from 'axios';
import { XMLHttpRequest } from 'xmlhttprequest';

global.XMLHttpRequest = XMLHttpRequest;

describe('hellos', () => {
  test('hellos', async () => {
    const response = await axios.post('http://localhost:9999/graphql', {
      query: `
        {
          hellos(after: "cursor") {
            edges {
              cursor
              node {
                id
                message
                create_time
                update_time
              }
            }
          }
        }
      `,
    });

    const { data } = response;
    // expect(data).toMatchObject({
    //   data: {
    //     hellos: [],
    //   },
    // });
    expect(Object.keys(data)).toEqual(
        expect.arrayContaining(["name", "lastname", "role", "age"])
    );
  });

});
